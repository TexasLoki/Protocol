package org.pistonmc.protocol.v47.play.client;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.IncomingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.packet.UnreadPacket;

import java.io.IOException;

public class PacketPlayInUseEntity extends IncomingPacket {

    private int target;
    private byte mouse;

    public PacketPlayInUseEntity() {
        super(ProtocolState.PLAY, 0x02);
    }

    @Override
    public void read(UnreadPacket packet) throws PacketException, IOException {
        this.target = packet.getStream().readInt();
        this.mouse = packet.getStream().readByte();
    }

    public int getTarget() {
        return target;
    }

    public byte getMouse() {
        return mouse;
    }

}
