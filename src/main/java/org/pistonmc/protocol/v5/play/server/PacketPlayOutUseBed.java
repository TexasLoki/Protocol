package org.pistonmc.protocol.v5.play.server;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.OutgoingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.stream.PacketOutputStream;
import org.pistonmc.protocol.v5.play.server.groups.PacketPlayOutPlayer;

import java.io.IOException;

public class PacketPlayOutUseBed extends PacketPlayOutPlayer {

    private int x;
    private int y;
    private int z;

    public PacketPlayOutUseBed(int entity, int x, int y, int z) {
        super(0x0A, entity);
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
        super.write(stream);
        stream.writeInt(x);
        stream.writeUnsignedByte(y);
        stream.writeInt(z);
    }

}
