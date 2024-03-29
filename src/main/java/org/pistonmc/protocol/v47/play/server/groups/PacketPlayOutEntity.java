package org.pistonmc.protocol.v47.play.server.groups;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.OutgoingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.stream.PacketOutputStream;

import java.io.IOException;

public class PacketPlayOutEntity extends OutgoingPacket {

    protected int entity;

    public PacketPlayOutEntity(int entity) {
        this(0x14, entity);
    }

    public PacketPlayOutEntity(int id, int entity) {
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
