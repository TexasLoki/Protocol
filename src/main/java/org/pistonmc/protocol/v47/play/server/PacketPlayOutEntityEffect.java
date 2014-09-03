package org.pistonmc.protocol.v47.play.server;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.stream.PacketOutputStream;
import org.pistonmc.protocol.v47.play.server.groups.PacketPlayOutEntity;

import java.io.IOException;

public class PacketPlayOutEntityEffect extends PacketPlayOutEntity {

    private byte effect;
    private byte amplifier;
    private short duration;

    public PacketPlayOutEntityEffect(int entity, byte effect, byte amplifier, short duration) {
        super(entity, 0x1D);
        this.effect = effect;
        this.amplifier = amplifier;
        this.duration = duration;
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        super.write(stream);
        stream.writeByte(effect);
        stream.writeByte(amplifier);
        stream.writeShort(duration);
    }

}