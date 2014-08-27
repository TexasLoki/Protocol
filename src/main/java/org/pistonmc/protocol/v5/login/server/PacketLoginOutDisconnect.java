package org.pistonmc.protocol.v5.login.server;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.OutgoingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.stream.PacketOutputStream;
import org.pistonmc.util.ChatFormatter;

import java.io.IOException;

public class PacketLoginOutDisconnect extends OutgoingPacket {

    private String message;

    public PacketLoginOutDisconnect(String message) {
        super(ProtocolState.LOGIN, 0x00);
        this.message = message;
    }

    public PacketLoginOutDisconnect(String message, boolean serialize) {
        this(serialize ? ChatFormatter.serialize(message) : message);
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        stream.writeString(message);
    }

}
