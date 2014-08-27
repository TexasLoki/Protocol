package org.pistonmc.protocol.v5.login.client;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.packet.IncomingPacket;
import org.pistonmc.protocol.packet.UnreadPacket;

import java.io.IOException;

public class PacketLoginInLoginStart extends IncomingPacket {

    private String name;

    public PacketLoginInLoginStart() {
        super(ProtocolState.LOGIN, 0x00);
    }

    public String getName() {
        return name;
    }

    @Override
    public void read(UnreadPacket packet) throws PacketException, IOException {
        this.name = packet.getStream().readString();
    }

}
