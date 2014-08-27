package org.pistonmc.protocol.v5.play.client;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.IncomingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.packet.UnreadPacket;

import java.io.IOException;

public class PacketPlayInTabComplete extends IncomingPacket {

    private String text;

    public PacketPlayInTabComplete() {
        super(ProtocolState.PLAY, 0x14);
    }

    @Override
    public void read(UnreadPacket packet) throws PacketException, IOException {
        this.text = packet.getStream().readString();
    }

    public String getText() {
        return text;
    }
}
