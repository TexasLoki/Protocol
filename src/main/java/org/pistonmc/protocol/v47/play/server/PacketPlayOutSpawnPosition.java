package org.pistonmc.protocol.v47.play.server;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.OutgoingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.stream.PacketOutputStream;

import java.io.IOException;

public class PacketPlayOutSpawnPosition extends OutgoingPacket {

    private int x;
    private int y;
    private int z;

    public PacketPlayOutSpawnPosition(int x, int y, int z) {
        super(ProtocolState.PLAY, 0x05);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        stream.writeInt(x);
        stream.writeInt(y);
        stream.writeInt(z);
    }

}
