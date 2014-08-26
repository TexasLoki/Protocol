package org.pistonmc.protocol.v5.play.server;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.stream.PacketOutputStream;
import org.pistonmc.protocol.v5.play.server.groups.PacketPlayOutEntity;

import java.io.IOException;

public class PacketPlayOutEntityLook extends PacketPlayOutEntity {

    private float yaw;
    private float pitch;

    public PacketPlayOutEntityLook(int entity, float yaw, float pitch) {
        super(0x16, entity);
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
