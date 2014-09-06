package org.pistonmc.protocol.v47;

import io.netty.channel.ChannelHandlerContext;
import org.pistonmc.ChatColor;
import org.pistonmc.Piston;
import org.pistonmc.event.EventHandler;
import org.pistonmc.event.connection.ServerListPingEvent;
import org.pistonmc.event.packet.ReceivedPacketEvent;
import org.pistonmc.event.packet.SendPacketEvent;
import org.pistonmc.event.packet.SentPacketEvent;
import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.plugin.protocol.Protocol;
import org.pistonmc.plugin.protocol.ProtocolManager;
import org.pistonmc.protocol.PlayerConnection;
import org.pistonmc.protocol.older.v4.ProtocolV4;
import org.pistonmc.protocol.packet.IncomingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.v47.login.client.PacketLoginInEncryptionResponse;
import org.pistonmc.protocol.v47.login.client.PacketLoginInLoginStart;
import org.pistonmc.protocol.v47.login.server.PacketLoginOutDisconnect;
import org.pistonmc.protocol.v47.login.server.PacketLoginOutEncryptionRequest;
import org.pistonmc.protocol.v47.login.server.PacketLoginOutLoginSuccess;
import org.pistonmc.protocol.v47.play.client.*;
import org.pistonmc.protocol.v47.play.server.PacketPlayOutDisconnect;
import org.pistonmc.protocol.v47.status.client.PacketStatusInPing;
import org.pistonmc.protocol.v47.status.client.PacketStatusInRequest;
import org.pistonmc.protocol.v47.status.server.PacketStatusOutPing;
import org.pistonmc.protocol.v47.status.server.PacketStatusOutResponse;
import org.pistonmc.stickypiston.auth.AuthenticationHandler;
import org.pistonmc.stickypiston.auth.YggdrasilAuthenticationHandler;
import org.pistonmc.stickypiston.network.player.PlayerConnectionHandler;
import org.pistonmc.util.EncryptionUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.Arrays;
import java.util.UUID;

public class ProtocolV5 extends Protocol {

    private PacketLoginOutEncryptionRequest encryptionRequest;
    private String username;

    public ProtocolV5() {
        super(5);
    }

    public ProtocolV5(ProtocolV5 parent, PlayerConnection connection, ProtocolManager manager) {
        super(parent, connection, manager);
    }

    @Override
    public void onLoad() {
        ProtocolManager manager = Piston.getProtocolManager();
        manager.load(new ProtocolV4(this, manager), this, "StickyPiston 1.7-fallback", "4", getDescription().getAuthors(), true);

        // Play Packets
        add(new PacketPlayInKeepAlive());
        add(new PacketPlayInChatMessage());
        add(new PacketPlayInUseEntity());
        add(new PacketPlayInPlayer());
        add(new PacketPlayInPlayerPosition());
        add(new PacketPlayInPlayerLook());
        add(new PacketPlayInPlayerPositionAndLook());
        add(new PacketPlayInPlayerDigging());
        add(new PacketPlayInPlayerBlockPlacement());
        add(new PacketPlayInHeldItemChange());
        add(new PacketPlayInAnimation());
        add(new PacketPlayInEntityAction());
        add(new PacketPlayInSteerVehicle());
        add(new PacketPlayInCloseWindow());

        add(new PacketPlayInConfirmTransaction());

        add(new PacketPlayInEnchantItem());
        add(new PacketPlayInUpdateSign());
        add(new PacketPlayInPlayerAbilities());
        add(new PacketPlayInTabComplete());
        add(new PacketPlayInClientSettings());

        add(new PacketPlayInPluginMessage());

        // Status Packets
        add(new PacketStatusInRequest());
        add(new PacketStatusInPing());

        // Login Packets
        add(new PacketLoginInLoginStart());
        add(new PacketLoginInEncryptionResponse());
    }

    @Override
    public void handle(IncomingPacket packet, ChannelHandlerContext ctx) throws PacketException, IOException {
        if (packet instanceof PacketStatusInRequest) {
            ServerListPingEvent event = response();
            Piston.getEventManager().call(event);
            if (event.isCancelled()) {
                return;
            }
            connection.sendPacket(new PacketStatusOutResponse(event));
        } else if (packet instanceof PacketStatusInPing) {
            connection.sendPacket(new PacketStatusOutPing(((PacketStatusInPing) packet).getTime()));
            connection.close();
        } else if (packet instanceof PacketLoginInLoginStart && !Piston.getConfig().getBoolean("settings.offline")) {
            PacketLoginInLoginStart start = (PacketLoginInLoginStart) packet;
            Piston.getLogger().info("Starting login for " + start.getName() + "...");
            byte[] pubKey = EncryptionUtils.generateX509Key(EncryptionUtils.getKeys().getPublic()).getEncoded();
            byte[] verify = EncryptionUtils.generateVerifyToken();
            encryptionRequest = new PacketLoginOutEncryptionRequest("", pubKey, verify);
            username = start.getName();
            connection.sendPacket(encryptionRequest);
        } else if (packet instanceof PacketLoginInLoginStart) {
            connect(UUID.randomUUID());
        } else if (packet instanceof PacketLoginInEncryptionResponse) {
            PacketLoginInEncryptionResponse p = (PacketLoginInEncryptionResponse) packet;
            final PrivateKey privateKey = EncryptionUtils.getKeys().getPrivate();
            Cipher rsaCipher;
            try {
                rsaCipher = Cipher.getInstance("RSA");
            } catch (GeneralSecurityException ex) {
                Piston.getLogger().severe(ex);
                disconnect("There was an error whilst logging you in.");
                return;
            }
            SecretKey sharedSecret;
            try {
                rsaCipher.init(Cipher.DECRYPT_MODE, privateKey);
                sharedSecret = new SecretKeySpec(rsaCipher.doFinal(p.getSharedSecret()), "AES");
            } catch (Exception ex) {
                Piston.getLogger().severe(ex);
                disconnect("There was an error whilst logging you in.");
                return;
            }
            byte[] verifyToken;
            try {
                rsaCipher.init(Cipher.DECRYPT_MODE, privateKey);
                verifyToken = rsaCipher.doFinal(p.getVerifyToken());
            } catch (Exception ex) {
                Piston.getLogger().severe(ex);
                disconnect("There was an error whilst logging you in.");
                return;
            }
            if (!Arrays.equals(verifyToken, encryptionRequest.getVerifyToken())) {
                disconnect("There was an error whilst logging you in.");
                return;
            }

            connection.secure(sharedSecret);

            String hash;
            try {
                final MessageDigest digest = MessageDigest.getInstance("SHA-1");
                digest.update(encryptionRequest.getSessionId().getBytes());
                digest.update(sharedSecret.getEncoded());
                digest.update(EncryptionUtils.getKeys().getPublic().getEncoded());
                hash = new BigInteger(digest.digest()).toString(16);
            } catch (NoSuchAlgorithmException ex) {
                Piston.getLogger().severe(ex);
                disconnect("There was an error whilst logging you in.");
                return;
            }

            AuthenticationHandler authHandler = ((PlayerConnectionHandler) connection).getAuthenticator();
            if (authHandler instanceof YggdrasilAuthenticationHandler) {
                final YggdrasilAuthenticationHandler handler = (YggdrasilAuthenticationHandler) authHandler;
                handler.auth(username, hash, new YggdrasilAuthenticationHandler.Callback() {

                    @Override
                    public void onExecute(boolean success) {
                        if (success) {
                            Piston.getLogger().info("Connecting " + username + " to the server... {" + handler.getUUID().toString() + "}");
                            try {
                                // disconnect(ChatColor.GREEN + "Successfully logged in!");
                                connect(handler.getUUID());
                            } catch (Exception e) {
                                Piston.getLogger().warning(e);
                            }
                        } else {
                            Piston.getLogger().info("Failed to authenticate " + username + ".");
                            try {
                                disconnect(ChatColor.RED + "Failed to authenticate.");
                            } catch (Exception e) {
                                Piston.getLogger().warning(e);
                            }
                        }
                    }

                });
            }
        }
    }

    public void connect(UUID uuid) throws IOException, PacketException {
        connection.sendPacket(new PacketLoginOutLoginSuccess(uuid, username));
        // connection.sendPacket(new PacketPlayOutJoinGame());
    }

    public void disconnect(String message) {
        ProtocolState state = connection.getState();
        try {
            if (state == ProtocolState.LOGIN) {
                connection.sendPacket(new PacketLoginOutDisconnect(message, true));
            } else if (state == ProtocolState.PLAY) {
                connection.sendPacket(new PacketPlayOutDisconnect(message, true));
            }
        } catch (Exception ex) {
            getLogger().log("Could not send disconnect packet: ", ex);
        }

        connection.close();
    }

    @EventHandler
    public void onSend(SendPacketEvent event) {
        getLogger().debug("Send: " + event.getPacket());
    }

    @EventHandler
    public void onSent(SentPacketEvent event) {
        getLogger().debug("Sent: " + event.getPacket());
    }

    @EventHandler
    public void onReceived(ReceivedPacketEvent event) {
        getLogger().debug("Received: " + event.getPacket());
    }

    @Override
    public ProtocolV5 create(PlayerConnection connection, ProtocolManager manager) {
        return new ProtocolV5(this, connection, manager);
    }

}
