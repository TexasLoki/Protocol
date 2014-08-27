package org.pistonmc.protocol.v5.play.client;

import org.pistonmc.data.Action;
import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.packet.UnreadPacket;
import org.pistonmc.protocol.packet.IncomingPacket;

import java.io.IOException;

public class PacketPlayInEntityAction extends IncomingPacket {

	private int entityId;
	private Action action;
	private int jumpBoost;

	public PacketPlayInEntityAction() {
		super(ProtocolState.PLAY, 0x0B);
	}

	@Override
	public void read(UnreadPacket packet) throws PacketException, IOException {
		this.entityId = packet.getStream().readInt();
		this.action = Action.getAction(packet.getStream().readByte());
		this.jumpBoost = packet.getStream().readInt();
	}

	public int getEntityId() {
		return entityId;
	}

	public Action getAction() {
		return action;
	}

	public int getJumpBoost() {
		return jumpBoost;
	}

}