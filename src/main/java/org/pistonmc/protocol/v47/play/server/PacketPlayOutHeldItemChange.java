package org.pistonmc.protocol.v47.play.server;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.OutgoingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.stream.PacketOutputStream;

import java.io.IOException;

public class PacketPlayOutHeldItemChange extends OutgoingPacket {

    private int slot;

    public PacketPlayOutHeldItemChange(int slot) {
        super(ProtocolState.PLAY, 0x09);
        this.slot = slot;
    }

    public int getSlot() {
        return slot;
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        stream.writeByte(slot);
    }

}
