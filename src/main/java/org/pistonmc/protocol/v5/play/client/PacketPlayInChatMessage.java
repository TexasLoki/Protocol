package org.pistonmc.protocol.v5.play.client;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.IncomingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.packet.UnreadPacket;

import java.io.IOException;

public class PacketPlayInChatMessage extends IncomingPacket {

    private String message;

    public PacketPlayInChatMessage() {
        super(ProtocolState.PLAY, 0x01);
    }

    @Override
    public void read(UnreadPacket packet) throws PacketException, IOException {
        this.message = packet.getStream().readString();
    }

    public String getMessage() {
        return message;
    }

}
