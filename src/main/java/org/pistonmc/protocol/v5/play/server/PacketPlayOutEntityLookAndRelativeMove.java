package org.pistonmc.protocol.v5.play.server;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.stream.PacketOutputStream;
import org.pistonmc.protocol.v5.play.server.groups.PacketPlayOutEntity;

import java.io.IOException;

public class PacketPlayOutEntityLookAndRelativeMove extends PacketPlayOutEntityRelativeMove {

    private float yaw;
    private float pitch;

    public PacketPlayOutEntityLookAndRelativeMove(int entity, double x, double y, double z, float yaw, float pitch) {
        super(0x17, entity, x, y, z);
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
