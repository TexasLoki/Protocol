package org.pistonmc.protocol.v47.play.server;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.stream.PacketOutputStream;
import org.pistonmc.protocol.v47.play.server.groups.PacketPlayOutEntity;

import java.io.IOException;

public class PacketPlayOutEntityVelocity extends PacketPlayOutEntity {

    private int x;
    private int y;
    private int z;

    public PacketPlayOutEntityVelocity(int entity, int x, int y, int z) {
        super(0x12, entity);
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
        stream.writeShort(x);
        stream.writeShort(y);
        stream.writeShort(z);
    }

}
