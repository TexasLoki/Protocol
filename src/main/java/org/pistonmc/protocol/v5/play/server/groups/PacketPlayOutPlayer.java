package org.pistonmc.protocol.v5.play.server.groups;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.stream.PacketOutputStream;

import java.io.IOException;

public class PacketPlayOutPlayer extends PacketPlayOutEntity {

    public PacketPlayOutPlayer(int id, int entity) {
        super(id, entity);
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        stream.writeInt(entity);
    }

}
