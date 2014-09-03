package org.pistonmc.protocol.v47.play.server;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.stream.PacketOutputStream;
import org.pistonmc.protocol.v47.play.server.groups.PacketPlayOutEntity;

import java.io.IOException;

public class PacketPlayOutEntityRelativeMove extends PacketPlayOutEntity {

    private double x;
    private double y;
    private double z;

    public PacketPlayOutEntityRelativeMove(int id, int entity, double x, double y, double z) {
        super(id, entity);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public PacketPlayOutEntityRelativeMove(int entity, double x, double y, double z) {
        this(0x15, entity, x, y, z);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        super.write(stream);
        stream.writeFixedPointByte(x);
        stream.writeFixedPointByte(y);
        stream.writeFixedPointByte(z);
    }

}
