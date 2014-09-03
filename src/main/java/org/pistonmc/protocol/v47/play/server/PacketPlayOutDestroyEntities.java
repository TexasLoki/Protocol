package org.pistonmc.protocol.v47.play.server;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.OutgoingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.stream.PacketOutputStream;

import java.io.IOException;

public class PacketPlayOutDestroyEntities extends OutgoingPacket {

    private int[] ids;

    public PacketPlayOutDestroyEntities(int[] ids) {
        super(ProtocolState.PLAY, 0x13);
        this.ids = ids;
    }

    public int[] getIds() {
        return ids;
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        stream.writeByte(ids.length);
        for (int i : ids) {
            stream.writeInt(i);
        }
    }

}
