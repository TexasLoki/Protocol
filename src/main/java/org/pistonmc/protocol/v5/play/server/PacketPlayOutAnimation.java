package org.pistonmc.protocol.v5.play.server;

import org.pistonmc.data.Animation;
import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.stream.PacketOutputStream;
import org.pistonmc.protocol.v5.play.server.groups.PacketPlayOutEntity;

import java.io.IOException;

public class PacketPlayOutAnimation extends PacketPlayOutEntity {

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
        super.write(stream);
        stream.writeUnsignedByte(animation.getId());
    }

}
