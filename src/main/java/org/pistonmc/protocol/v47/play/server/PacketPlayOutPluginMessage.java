package org.pistonmc.protocol.v47.play.server;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.OutgoingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.stream.PacketOutputStream;

import java.io.IOException;

public class PacketPlayOutPluginMessage extends OutgoingPacket {

    private String channel;
    private short length;
    private byte[] data;

    public PacketPlayOutPluginMessage(String channel, short length, byte[] data) {
        super(ProtocolState.PLAY, 0x3F);
        this.channel = channel;
        this.length = length;
        this.data = data;
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        stream.writeString(channel);
        stream.writeShort(length);
        stream.writeByteArray(data);
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