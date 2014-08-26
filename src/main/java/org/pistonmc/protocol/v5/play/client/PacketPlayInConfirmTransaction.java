package org.pistonmc.protocol.v5.play.client;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.IncomingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.packet.UnreadPacket;

import java.io.IOException;

public class PacketPlayInConfirmTransaction  extends IncomingPacket {

	private byte windowId;
	private short actionNumber;
	private boolean accepted;

	public PacketPlayInConfirmTransaction() {
		super(ProtocolState.PLAY, 0x0F);
	}

	@Override
	public void read(UnreadPacket packet) throws PacketException, IOException {
		this.windowId = packet.getStream().readByte();
		this.actionNumber = packet.getStream().readShort();
		this.accepted = packet.getStream().readBoolean();
	}

	public byte getWindowId() {
		return windowId;
	}

	public short getActionNumber() {
		return actionNumber;
	}

	public boolean isAccepted() {
		return accepted;
	}

}
