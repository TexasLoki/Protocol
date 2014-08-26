package org.pistonmc.protocol.v5.play.client;


import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.IncomingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.packet.UnreadPacket;

import java.io.IOException;

public class PacketPlayInPluginMessage extends IncomingPacket {

	private String channel;
	private short length;
	private byte[] data;

	public PacketPlayInPluginMessage() {
		super(ProtocolState.PLAY, 0x17);
	}

	@Override
	public void read(UnreadPacket packet) throws PacketException, IOException {
		this.channel = packet.getStream().readString();
		this.length = packet.getStream().readShort();
		this.data = packet.getStream().readBytes();
	}

	public String getChannel() {
		return channel;
	}

	public short getLength() {
		return length;
	}

	public byte[] getData() {
		return data;
	}

}
