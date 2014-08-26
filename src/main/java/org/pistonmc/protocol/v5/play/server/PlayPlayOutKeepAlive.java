package org.pistonmc.protocol.v5.play.server;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.OutgoingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.stream.PacketOutputStream;

import java.io.IOException;

public class PlayPlayOutKeepAlive extends OutgoingPacket {

    private int aliveId;

    public PlayPlayOutKeepAlive() {
        super(ProtocolState.PLAY, 0x00);
        this.aliveId = (int) (System.nanoTime() / 1000000L);
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        stream.writeInt(aliveId);
    }

}
