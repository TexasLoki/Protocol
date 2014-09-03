package org.pistonmc.protocol.v47.play.server;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.stream.PacketOutputStream;
import org.pistonmc.protocol.v47.play.server.groups.PacketPlayOutEntity;

import java.io.IOException;

public class PacketPlayOutEntityHeadLook extends PacketPlayOutEntity {

    private float yaw;

    public PacketPlayOutEntityHeadLook(int id, int entity, float yaw) {
        super(id, entity);
        this.yaw = yaw;
    }

    public PacketPlayOutEntityHeadLook(int entity, float yaw) {
        this(0x19, entity, yaw);
    }

    public float getYaw() {
        return yaw;
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        super.write(stream);
        stream.writeRotation(yaw);
    }

}
