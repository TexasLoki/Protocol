package org.pistonmc.protocol.v47.play.server;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.OutgoingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.stream.PacketOutputStream;

import java.io.IOException;

public class PacketPlayOutCollectItem extends OutgoingPacket {

    private int entity;
    private int collector;

    public PacketPlayOutCollectItem(int entity, int collector) {
        super(ProtocolState.PLAY, 0x0D);
        this.entity = entity;
        this.collector = collector;
    }

    public int getEntity() {
        return entity;
    }

    public int getCollector() {
        return collector;
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        stream.writeInt(entity);
        stream.writeInt(collector);
    }

}
