package org.pistonmc.protocol.v5.play.client;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.IncomingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.packet.UnreadPacket;

import java.io.IOException;

public class PacketPlayInUpdateSign extends IncomingPacket {

    private int posX;
    private int posY;
    private int posZ;
    private String[] lines;

    public PacketPlayInUpdateSign() {
        super(ProtocolState.PLAY, 0x12);
    }

    @Override
    public void read(UnreadPacket packet) throws PacketException, IOException {
        this.posX = packet.getStream().readInt();
        this.posY = packet.getStream().readShort();
        this.posZ = packet.getStream().readInt();
        this.lines = new String[4];

        for (int i = 0; i < 4; i++) {
            this.lines[i] = packet.getStream().readString();
        }
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getPosZ() {
        return posZ;
    }

    public String[] getLines() {
        return lines;
    }
}
