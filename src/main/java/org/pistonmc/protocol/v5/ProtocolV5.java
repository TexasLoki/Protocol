package org.pistonmc.protocol.v5;

import org.json.JSONObject;
import org.pistonmc.Piston;
import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.plugin.protocol.Protocol;
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

    public ProtocolV5(ProtocolV5 parent, PlayerConnection connection) {
        super(parent, connection);
    }

    @Override
    public void onLoad() {
        Piston.getProtocolManager().load(new ProtocolV4(), this, "1.7", "4");

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
        if(packet instanceof PacketStatusInRequest) {
            connection.sendPacket(new PacketStatusOutResponse(new JSONObject()));
        } else if(packet instanceof PacketStatusInPing) {
            connection.sendPacket(new PacketStatusOutPing(((PacketStatusInPing) packet).getTime()));
            connection.close();
        } else if(packet instanceof PacketLoginInLoginStart) {
            connection.sendPacket(new PacketLoginOutDisconnect("StickyPiston servers can't be joined yet"));
            connection.close();
        }
    }

    @Override
    public ProtocolV5 create(PlayerConnection connection) {
        return new ProtocolV5(this, connection);
    }

}
