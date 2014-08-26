package org.pistonmc.protocol.v5.play.client;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.IncomingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.packet.UnreadPacket;

import java.io.IOException;

public class PacketPlayInKeepAlive extends IncomingPacket {

	private int aliveId;

	public PacketPlayInKeepAlive() {
		super(ProtocolState.PLAY, 0x00);
	}

	@Override
	public void read(UnreadPacket packet) throws PacketException, IOException {
		this.aliveId = packet.getStream().readInt();
	}

	public int getAliveId() {
		return aliveId;
	}

}

