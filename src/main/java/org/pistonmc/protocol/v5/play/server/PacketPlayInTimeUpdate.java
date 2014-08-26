package in.parapengu.craftbot.protocol.v4.play.server;

import in.parapengu.craftbot.protocol.Destination;
import in.parapengu.craftbot.protocol.Packet;
import in.parapengu.craftbot.protocol.PacketException;
import in.parapengu.craftbot.protocol.stream.PacketInputStream;
import in.parapengu.craftbot.protocol.stream.PacketOutputStream;

import java.io.IOException;

public class PacketPlayInTimeUpdate extends Packet {

	private long age;
	private long time;

	public PacketPlayInTimeUpdate() {
		super(0x05);
	}

	@Override
	public void build(PacketInputStream input) throws IOException {
		this.age = input.readLong();
		this.time = input.readLong();
	}

	@Override
	public void send(PacketOutputStream output) throws IOException {
		throw new PacketException("Can not send an inbound packet", getClass(), Destination.SERVER);
	}

	public long getAge() {
		return age;
	}

	public long getTime() {
		return time;
	}

}
