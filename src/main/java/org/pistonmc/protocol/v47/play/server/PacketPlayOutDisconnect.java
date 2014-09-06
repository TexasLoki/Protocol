package org.pistonmc.protocol.v47.play.server;


import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.OutgoingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.stream.PacketOutputStream;
import org.pistonmc.util.ChatFormatter;

import java.io.IOException;

public class PacketPlayOutDisconnect extends OutgoingPacket {

    private String message;

    public PacketPlayOutDisconnect(String message) {
        this(message, false);
    }

    public PacketPlayOutDisconnect(String message, boolean serialize) {
        super(ProtocolState.PLAY, 0x40);
        this.message = serialize ? ChatFormatter.serialize(message).toString() : message;
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        stream.writeString(message);
    }

    public String getMessage() {
        return message;
    }

}
