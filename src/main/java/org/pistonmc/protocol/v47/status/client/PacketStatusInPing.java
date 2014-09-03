package org.pistonmc.protocol.v47.status.client;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.IncomingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.packet.UnreadPacket;

import java.io.IOException;

public class PacketStatusInPing extends IncomingPacket {

    private long time;

    public PacketStatusInPing() {
        super(ProtocolState.STATUS, 0x01);
    }

    public long getTime() {
        return time;
    }

    @Override
    public void read(UnreadPacket packet) throws PacketException, IOException {
        this.time = packet.getStream().readLong();
    }

}
