package org.pistonmc.protocol.v5.play.client;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.IncomingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.packet.UnreadPacket;

import java.io.IOException;

public class PacketPlayInPlayer extends IncomingPacket {

	private boolean onGround;

	public PacketPlayInPlayer() {
		super(ProtocolState.PLAY, 0x03);
	}

	@Override
	public void read(UnreadPacket packet) throws PacketException, IOException {
		this.onGround = packet.getStream().readBoolean();
	}

	public boolean isOnGround() {
		return onGround;
	}

}
