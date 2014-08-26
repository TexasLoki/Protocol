package org.pistonmc.protocol.v5.play.client;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.IncomingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.packet.UnreadPacket;

import java.io.IOException;

public class PacketPlayInEnchantItem  extends IncomingPacket {

	private byte windowId;
	private byte enchantment;

	public PacketPlayInEnchantItem() {
		super(ProtocolState.PLAY, 0x11);
	}

	@Override
	public void read(UnreadPacket packet) throws PacketException, IOException {
		this.windowId = packet.getStream().readByte();
		this.enchantment = packet.getStream().readByte();
	}

	public byte getWindowId() {
		return windowId;
	}

	public byte getEnchantment() {
		return enchantment;
	}

}
