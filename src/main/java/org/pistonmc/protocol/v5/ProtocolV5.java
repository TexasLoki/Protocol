package org.pistonmc.protocol.v5;

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
import org.pistonmc.protocol.v5.login.client.PacketLoginInEncryptionResponse;
import org.pistonmc.protocol.v5.login.client.PacketLoginInLoginStart;
import org.pistonmc.protocol.v5.login.server.PacketLoginOutDisconnect;
import org.pistonmc.protocol.v5.login.server.PacketLoginOutEncryptionRequest;
import org.pistonmc.protocol.v5.play.client.*;
import org.pistonmc.protocol.v5.status.client.PacketStatusInPing;
import org.pistonmc.protocol.v5.status.client.PacketStatusInRequest;
import org.pistonmc.protocol.v5.status.server.PacketStatusOutPing;
import org.pistonmc.protocol.v5.status.server.PacketStatusOutResponse;
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
import java.util.Random;

public class ProtocolV5 extends Protocol {

    private static final Random RANDOM = new Random();

    public ProtocolV5() {
        super(5);
    }

    public ProtocolV5(ProtocolV5 parent, PlayerConnection connection, ProtocolManager manager) {
        super(parent, connection, manager);
    }

    @Override
    public void onLoad() {
        ProtocolManager manager = Piston.getProtocolManager();
        manager.load(new ProtocolV4(), this, "StickyPiston 1.7-fallback", "4", getDescription().getAuthors(), true);

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

    private PacketLoginOutEncryptionRequest encryptionRequest;

    @Override
    public void handle(IncomingPacket packet) throws PacketException, IOException {
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
        } else if (packet instanceof PacketLoginInLoginStart) {
            PlayerConnectionHandler handler = (PlayerConnectionHandler) connection;
            if (!Piston.getConfig().getBoolean("offline")) {
                Piston.getLogger().info("Starting login for " + ((PacketLoginInLoginStart) packet).getName() + "...");
                String hash = Long.toString(RANDOM.nextLong(), 16);
                byte[] pubKey = EncryptionUtils.getKeys().getPublic().getEncoded();
                byte[] verify = new byte[4];
                RANDOM.nextBytes(verify);
                encryptionRequest = new PacketLoginOutEncryptionRequest(hash, pubKey, verify);
                connection.sendPacket(encryptionRequest);
            } else {
                // Send login success
            }
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
        }
    }

    public void disconnect(String reason) throws IOException, PacketException {
        connection.sendPacket(new PacketLoginOutDisconnect(reason, true));
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
