package org.pistonmc.protocol.v5.login.client;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.IncommingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.packet.UnreadPacket;

import java.io.IOException;

public class PacketLoginInStart extends IncommingPacket {

	private String username;

	public PacketLoginInStart() {
		super(ProtocolState.LOGIN, 0x00);
	}

    @Override
    public void read(UnreadPacket packet) throws PacketException, IOException {
        this.username = packet.getStream().readString();
    }

	public String getUsername() {
		return username;
	}

}
