package org.pistonmc.protocol.v5.play.server.groups;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.OutgoingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.stream.PacketOutputStream;

import java.io.IOException;

public class PacketPlayOutPlayer extends OutgoingPacket {

    protected int entity;

    public PacketPlayOutPlayer(int id, int entity) {
        super(ProtocolState.PLAY, id);
        this.entity = entity;
    }

    public int getEntity() {
        return entity;
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        stream.writeInt(entity);
    }

}
