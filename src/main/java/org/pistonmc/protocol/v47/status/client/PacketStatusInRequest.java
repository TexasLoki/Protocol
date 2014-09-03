package org.pistonmc.protocol.v47.status.client;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.IncomingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.packet.UnreadPacket;

import java.io.IOException;

public class PacketStatusInRequest extends IncomingPacket {

    public PacketStatusInRequest() {
        super(ProtocolState.STATUS, 0x00);
    }

    @Override
    public void read(UnreadPacket packet) throws PacketException, IOException {
    }

}
