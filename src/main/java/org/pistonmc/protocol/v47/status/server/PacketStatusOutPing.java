package org.pistonmc.protocol.v47.status.server;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.OutgoingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.stream.PacketOutputStream;

import java.io.IOException;

public class PacketStatusOutPing extends OutgoingPacket {

    private long time;

    public PacketStatusOutPing(long time) {
        super(ProtocolState.STATUS, 0x01);
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        stream.writeLong(time);
    }

}
