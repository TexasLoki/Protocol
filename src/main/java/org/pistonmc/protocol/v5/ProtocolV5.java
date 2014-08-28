package org.pistonmc.protocol.v5;

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
import org.pistonmc.protocol.v5.login.client.PacketLoginInLoginStart;
import org.pistonmc.protocol.v5.login.server.PacketLoginOutDisconnect;
import org.pistonmc.protocol.v5.play.client.*;
import org.pistonmc.protocol.v5.status.client.PacketStatusInPing;
import org.pistonmc.protocol.v5.status.client.PacketStatusInRequest;
import org.pistonmc.protocol.v5.status.server.PacketStatusOutPing;
import org.pistonmc.protocol.v5.status.server.PacketStatusOutResponse;

import java.io.IOException;

public class ProtocolV5 extends Protocol {

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
    }

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
            connection.sendPacket(new PacketLoginOutDisconnect(ChatColor.RED + "-> StickyPiston <-\n\n" + ChatColor.GOLD + "This server can't be joined yet.", true));
            connection.close();
        }
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
