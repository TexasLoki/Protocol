package org.pistonmc.protocol.older.v4;

import io.netty.channel.ChannelHandlerContext;
import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.plugin.protocol.Protocol;
import org.pistonmc.plugin.protocol.ProtocolManager;
import org.pistonmc.protocol.PlayerConnection;
import org.pistonmc.protocol.packet.IncomingPacket;
import org.pistonmc.protocol.v47.ProtocolV5;
import org.pistonmc.protocol.v47.login.client.PacketLoginInLoginStart;
import org.pistonmc.protocol.v47.login.server.PacketLoginOutDisconnect;
import org.pistonmc.protocol.v47.status.client.PacketStatusInPing;
import org.pistonmc.protocol.v47.status.server.PacketStatusOutPing;

import java.io.IOException;

public class ProtocolV4 extends Protocol {

    private ProtocolV5 parent;

    public ProtocolV4() {
        super(4);
    }

    public ProtocolV4(ProtocolV5 parent, ProtocolManager manager) {
        super(parent, manager);
        this.version = 4;
        this.parent = parent;
    }

    public ProtocolV4(Protocol parent, PlayerConnection connection, ProtocolManager manager) {
        super(parent, connection, manager);
        this.version = 4;
    }

    @Override
    public void handle(IncomingPacket packet, ChannelHandlerContext ctx) throws PacketException, IOException {
        if (parent != null) {
            parent.handle(packet, ctx);
        } else {
            if (packet instanceof PacketStatusInPing) {
                connection.sendPacket(new PacketStatusOutPing(((PacketStatusInPing) packet).getTime()));
                connection.close();
            } else if (packet instanceof PacketLoginInLoginStart) {
                connection.sendPacket(new PacketLoginOutDisconnect("Outdated Client"));
                connection.close();
            }
        }
    }

    @Override
    public Protocol create(PlayerConnection connection, ProtocolManager manager) {
        return new ProtocolV4(parent != null ? parent : this, connection, manager);
    }

}
