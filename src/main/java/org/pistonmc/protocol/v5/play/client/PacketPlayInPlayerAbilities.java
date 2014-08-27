package org.pistonmc.protocol.v5.play.client;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.IncomingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.packet.UnreadPacket;

import java.io.IOException;

public class PacketPlayInPlayerAbilities extends IncomingPacket {

    private byte flags;
    private float flyingSpeed;
    private float walkingSpeed;

    public PacketPlayInPlayerAbilities() {
        super(ProtocolState.PLAY, 0x13);
    }

    @Override
    public void read(UnreadPacket packet) throws PacketException, IOException {
        this.flags = packet.getStream().readByte();
        this.flyingSpeed = packet.getStream().readFloat();
        this.walkingSpeed = packet.getStream().readFloat();
    }

    public byte getFlags() {
        return flags;
    }

    public float getFlyingSpeed() {
        return flyingSpeed;
    }

    public float getWalkingSpeed() {
        return walkingSpeed;
    }
}
