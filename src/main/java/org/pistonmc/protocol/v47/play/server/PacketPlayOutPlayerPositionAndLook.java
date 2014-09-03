package org.pistonmc.protocol.v47.play.server;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.OutgoingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.stream.PacketOutputStream;

import java.io.IOException;

public class PacketPlayOutPlayerPositionAndLook extends OutgoingPacket {

    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;
    private boolean onGround;

    public PacketPlayOutPlayerPositionAndLook(double x, double y, double z, float yaw, float pitch, boolean onGround) {
        super(ProtocolState.PLAY, 0x08);
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
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

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public boolean isOnGround() {
        return onGround;
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        stream.writeDouble(x);
        stream.writeDouble(y);
        stream.writeDouble(z);
        stream.writeFloat(yaw);
        stream.writeFloat(pitch);
        stream.writeBoolean(onGround);
    }

}
