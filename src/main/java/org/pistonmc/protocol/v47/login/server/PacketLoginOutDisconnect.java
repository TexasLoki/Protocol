package org.pistonmc.protocol.v47.login.server;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.OutgoingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.stream.PacketOutputStream;
import org.pistonmc.util.ChatFormatter;

import java.io.IOException;

public class PacketLoginOutDisconnect extends OutgoingPacket {

    private String message;

    public PacketLoginOutDisconnect(String message) {
        this(message, false);
    }

    public PacketLoginOutDisconnect(String message, boolean serialize) {
        super(ProtocolState.LOGIN, 0x00);
        this.message = serialize ? ChatFormatter.serialize(message).toString() : message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        stream.writeString(message);
    }

}
