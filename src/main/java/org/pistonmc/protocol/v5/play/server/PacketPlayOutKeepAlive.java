package org.pistonmc.protocol.v5.play.server;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.OutgoingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.stream.PacketOutputStream;

import java.io.IOException;

public class PacketPlayOutKeepAlive extends OutgoingPacket {

    private int aliveId;

    public PacketPlayOutKeepAlive() {
        super(ProtocolState.PLAY, 0x00);
        this.aliveId = (int) (System.nanoTime() / 1000000L);
    }

    public int getAliveId() {
        return aliveId;
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        stream.writeInt(aliveId);
    }

}
