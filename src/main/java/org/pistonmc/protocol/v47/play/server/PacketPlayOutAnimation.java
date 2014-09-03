package org.pistonmc.protocol.v47.play.server;

import org.pistonmc.data.Animation;
import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.stream.PacketOutputStream;
import org.pistonmc.protocol.v47.play.server.groups.PacketPlayOutPlayer;

import java.io.IOException;

public class PacketPlayOutAnimation extends PacketPlayOutPlayer {

    private Animation animation;

    public PacketPlayOutAnimation(int entity, Animation animation) {
        super(0x0A, entity);
        this.animation = animation;
    }

    public Animation getAnimation() {
        return animation;
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        stream.writeVarInt(entity);
        stream.writeUnsignedByte(animation.getId());
    }

}
