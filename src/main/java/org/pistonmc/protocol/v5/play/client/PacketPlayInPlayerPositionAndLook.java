package org.pistonmc.protocol.v5.play.client;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.IncomingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.packet.UnreadPacket;

import java.io.IOException;

public class PacketPlayInPlayerPositionAndLook extends IncomingPacket {

    private double posX;
    private double feetY;
    private double headY;
    private double posZ;
    private float yaw;
    private float pitch;
    private boolean onGround;

    public PacketPlayInPlayerPositionAndLook() {
        super(ProtocolState.PLAY, 0x06);
    }

    @Override
    public void read(UnreadPacket packet) throws PacketException, IOException {
        this.posX = packet.getStream().readDouble();
        this.feetY = packet.getStream().readDouble();
        this.headY = packet.getStream().readDouble();
        this.posZ = packet.getStream().readDouble();
        this.yaw = packet.getStream().readFloat();
        this.pitch = packet.getStream().readFloat();
        this.onGround = packet.getStream().readBoolean();
    }

    public double getPosX() {
        return posX;
    }

    public double getFeetY() {
        return feetY;
    }

    public double getHeadY() {
        return headY;
    }

    public double getPosZ() {
        return posZ;
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

}
