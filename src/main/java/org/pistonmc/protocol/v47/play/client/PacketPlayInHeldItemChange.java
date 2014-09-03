package org.pistonmc.protocol.v47.play.client;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.IncomingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.packet.UnreadPacket;

import java.io.IOException;

public class PacketPlayInHeldItemChange extends IncomingPacket {

    private short slot;

    public PacketPlayInHeldItemChange() {
        super(ProtocolState.PLAY, 0x09);
    }

    @Override
    public void read(UnreadPacket packet) throws PacketException, IOException {
        this.slot = packet.getStream().readShort();
    }

    public short getSlot() {
        return slot;
    }
}
