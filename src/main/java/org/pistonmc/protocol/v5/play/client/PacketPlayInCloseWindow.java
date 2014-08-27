package org.pistonmc.protocol.v5.play.client;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.IncomingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.packet.UnreadPacket;

import java.io.IOException;

public class PacketPlayInCloseWindow extends IncomingPacket {

    private byte windowId;

    public PacketPlayInCloseWindow() {
        super(ProtocolState.PLAY, 0x0D);
    }

    @Override
    public void read(UnreadPacket packet) throws PacketException, IOException {
        this.windowId = packet.getStream().readByte();
    }

    public byte getWindowId() {
        return windowId;
    }

}
