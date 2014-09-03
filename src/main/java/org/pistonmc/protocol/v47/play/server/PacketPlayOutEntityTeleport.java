package org.pistonmc.protocol.v47.play.server;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.stream.PacketOutputStream;

import java.io.IOException;

public class PacketPlayOutEntityTeleport extends PacketPlayOutEntityRelativeMove {

    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;

    public PacketPlayOutEntityTeleport(int entity, double x, double y, double z, float yaw, float pitch) {
        super(0x18, entity, x, y, z);
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        super.write(stream);
        stream.writeRotation(yaw);
        stream.writeRotation(pitch);
    }

}
