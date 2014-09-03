package org.pistonmc.protocol.v47.play.server;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.stream.PacketOutputStream;
import org.pistonmc.protocol.v47.play.server.groups.PacketPlayOutEntity;

import java.io.IOException;

public class PacketPlayOutSpawnExperienceOrb extends PacketPlayOutEntity {

    private int x;
    private int y;
    private int z;
    private short count;

    public PacketPlayOutSpawnExperienceOrb(int entity, int x, int y, int z, short count) {
        super(0x11, entity);
        this.x = x;
        this.y = y;
        this.z = z;
        this.count = count;
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

    public short getCount() {
        return count;
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        stream.writeVarInt(entity);
        stream.writeInt(x);
        stream.writeInt(y);
        stream.writeInt(z);
        stream.writeShort(count);
    }

}
