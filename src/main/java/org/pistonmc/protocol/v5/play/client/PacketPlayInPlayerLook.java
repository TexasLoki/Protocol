package org.pistonmc.protocol.v5.play.client;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.IncomingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.packet.UnreadPacket;

import java.io.IOException;

public class PacketPlayInPlayerLook extends IncomingPacket {

    private float yaw;
    private float pitch;
    private boolean onGround;

    public PacketPlayInPlayerLook() {
        super(ProtocolState.PLAY, 0x05);
    }

    @Override
    public void read(UnreadPacket packet) throws PacketException, IOException {
        this.yaw = packet.getStream().readFloat();
        this.pitch = packet.getStream().readFloat();
        this.onGround = packet.getStream().readBoolean();
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
