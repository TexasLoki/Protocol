package org.pistonmc.protocol.v5.play.server;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.OutgoingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.stream.PacketOutputStream;

import java.io.IOException;

public class PacketPlayOutUpdateScore extends OutgoingPacket {

	private String itemName;
	private byte updateRemove;
	private String scoreName;
	private int value;

	public PacketPlayOutUpdateScore(String itemName, byte updateRemove, String scoreName, int value) {
		super(ProtocolState.PLAY, 0x3C);
		this.itemName = itemName;
		this.updateRemove = updateRemove;
		this.scoreName = scoreName;
		this.value = value;
	}

	@Override
	public void write(PacketOutputStream stream) throws PacketException, IOException {
		stream.writeString(itemName);
		stream.writeByte(updateRemove);
		stream.writeString(scoreName);
		stream.writeInt(value);
	}

	public String getItemName() {
		return itemName;
	}

	public byte getUpdateRemove() {
		return updateRemove;
	}

	public String getScoreName() {
		return scoreName;
	}

	public int getValue() {
		return value;
	}

}