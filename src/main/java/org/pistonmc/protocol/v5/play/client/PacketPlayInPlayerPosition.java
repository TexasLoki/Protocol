package org.pistonmc.protocol.v5.play.client;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.IncomingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.packet.UnreadPacket;

import java.io.IOException;

public class PacketPlayInPlayerPosition extends IncomingPacket {

    private double posX;
    private double feetY;
    private double headY;
    private double posZ;
    private boolean onGround;

    public PacketPlayInPlayerPosition() {
        super(ProtocolState.PLAY, 0x04);
    }

    @Override
    public void read(UnreadPacket packet) throws PacketException, IOException {
        this.posX = packet.getStream().readDouble();
        this.feetY = packet.getStream().readDouble();
        this.headY = packet.getStream().readDouble();
        this.posZ = packet.getStream().readDouble();
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

    public boolean isOnGround() {
        return onGround;
    }

}
