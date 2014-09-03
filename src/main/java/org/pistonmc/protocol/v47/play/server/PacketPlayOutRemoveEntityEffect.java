package org.pistonmc.protocol.v47.play.server;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.stream.PacketOutputStream;
import org.pistonmc.protocol.v47.play.server.groups.PacketPlayOutEntity;

import java.io.IOException;

public class PacketPlayOutRemoveEntityEffect extends PacketPlayOutEntity {

    private byte effect;

    public PacketPlayOutRemoveEntityEffect(int entity, byte effect) {
        super(entity, 0x1D);
        this.effect = effect;
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        super.write(stream);
        stream.writeByte(effect);
    }

}