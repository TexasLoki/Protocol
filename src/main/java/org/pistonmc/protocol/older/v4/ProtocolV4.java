package org.pistonmc.protocol.older.v4;

import org.json.JSONObject;
import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.plugin.protocol.Protocol;
import org.pistonmc.protocol.PlayerConnection;
import org.pistonmc.protocol.packet.IncomingPacket;
import org.pistonmc.protocol.v5.login.client.PacketLoginInLoginStart;
import org.pistonmc.protocol.v5.login.server.PacketLoginOutDisconnect;
import org.pistonmc.protocol.v5.status.client.PacketStatusInPing;
import org.pistonmc.protocol.v5.status.client.PacketStatusInRequest;
import org.pistonmc.protocol.v5.status.server.PacketStatusOutPing;
import org.pistonmc.protocol.v5.status.server.PacketStatusOutResponse;

import java.io.IOException;

public class ProtocolV4 extends Protocol {

    public ProtocolV4() {
        super(4);
    }

    public ProtocolV4(ProtocolV4 parent, PlayerConnection connection) {
        super(parent, connection);
    }

    @Override
    public void onLoad() {
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
            connection.sendPacket(new PacketLoginOutDisconnect("Outdated Client"));
            connection.close();
        }
    }

    @Override
    public Protocol create(PlayerConnection connection) {
        return new ProtocolV4(this, connection);
    }

}
