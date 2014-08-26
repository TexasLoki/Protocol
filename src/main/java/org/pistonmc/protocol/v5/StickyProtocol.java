package org.pistonmc.protocol.v5;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.plugin.protocol.Protocol;
import org.pistonmc.protocol.PlayerConnection;
import org.pistonmc.protocol.packet.Packet;

public class StickyProtocol extends Protocol {

    public StickyProtocol() {
        super(5);
    }

    public StickyProtocol(int version, PlayerConnection connection) {
        super(version, connection);
    }

    @Override
    public void onEnable() {
        // add packets here using add(Packet)
    }

    @Override
    public void handle(Packet packet) throws PacketException {

    }

    @Override
    public Protocol create(PlayerConnection connection) {
        return new StickyProtocol(version, connection);
    }

}
