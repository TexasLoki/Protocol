package org.pistonmc.protocol.v47.play.server;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.stream.PacketOutputStream;

import java.io.IOException;

public class PacketPlayOutEntityLook extends PacketPlayOutEntityHeadLook {

    private float pitch;

    public PacketPlayOutEntityLook(int entity, float yaw, float pitch) {
        super(0x16, entity, yaw);
        this.pitch = pitch;
    }

    public float getPitch() {
        return pitch;
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        super.write(stream);
        stream.writeRotation(pitch);
    }

}
