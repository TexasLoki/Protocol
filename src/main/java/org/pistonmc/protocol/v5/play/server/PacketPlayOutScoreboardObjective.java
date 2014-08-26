package org.pistonmc.protocol.v5.play.server;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.stream.PacketOutputStream;
import org.pistonmc.protocol.packet.OutgoingPacket;

import java.io.IOException;

public class PacketPlayOutScoreboardObjective extends OutgoingPacket {

	private String name;
	private String value;
	private byte cud;

	public PacketPlayOutScoreboardObjective(String name, String value, byte cud) {
		super(ProtocolState.PLAY, 0x3B);
		this.name = name;
		this.value = value;
		this.cud = cud;
	}

	@Override
	public void write(PacketOutputStream stream) throws PacketException, IOException {
		stream.writeString(name);
		stream.writeString(value);
		stream.writeByte(cud);
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public byte getCreateUpdateRemove() {
		return cud;
	}

}