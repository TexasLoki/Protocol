package org.pistonmc.protocol.v5.login.server;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.OutgoingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.stream.PacketOutputStream;

import java.io.IOException;
import java.util.UUID;

public class PacketLoginOutLoginSuccess extends OutgoingPacket {

    private UUID uuid;
    private String username;

    public PacketLoginOutLoginSuccess(UUID uuid, String username) {
        super(ProtocolState.LOGIN, 0x02);
        this.uuid = uuid;
        this.username = username;
    }

    public UUID getUUID() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        stream.writeString(uuid.toString());
        stream.writeString(username);
    }

}
