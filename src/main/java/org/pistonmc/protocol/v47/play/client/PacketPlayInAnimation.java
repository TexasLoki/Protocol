package org.pistonmc.protocol.v47.play.client;

import org.pistonmc.data.Animation;
import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.IncomingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.packet.UnreadPacket;

import java.io.IOException;

public class PacketPlayInAnimation extends IncomingPacket {

    private int entityId;
    private Animation animation;

    public PacketPlayInAnimation() {
        super(ProtocolState.PLAY, 0x0A);
    }

    @Override
    public void read(UnreadPacket packet) throws PacketException, IOException {
        this.entityId = packet.getStream().readInt();
        this.animation = Animation.valueOf(packet.getStream().readByte());
    }

    public int getEntityId() {
        return entityId;
    }

    public Animation getAnimation() {
        return animation;
    }

}
