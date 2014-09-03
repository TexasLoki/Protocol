package org.pistonmc.protocol.v47.play.server;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.OutgoingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.stream.PacketOutputStream;

import java.io.IOException;

public class PacketPlayOutSetExperience extends OutgoingPacket {

    private float bar;
    private short level;
    private short exp;

    public PacketPlayOutSetExperience(float bar, short level, short exp) {
        super(ProtocolState.PLAY, 0x1F);
        this.bar = bar;
        this.level = level;
        this.exp = exp;
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        stream.writeFloat(bar);
        stream.writeShort(level);
        stream.writeShort(exp);
    }

}