package org.pistonmc.protocol.v5.play.server;


import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.OutgoingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.stream.PacketOutputStream;

import java.io.IOException;

public class PacketPlayOutDisconnect extends OutgoingPacket {

    private String reason;

    public PacketPlayOutDisconnect(String reason) {
        super(ProtocolState.PLAY, 0x40);
        this.reason = reason;
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        stream.writeString(reason);
    }

    public String getReason() {
        return reason;
    }

}
