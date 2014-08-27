package org.pistonmc.protocol.v5.play.client;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.IncomingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.packet.UnreadPacket;

import java.io.IOException;

public class PacketPlayInPlayerDigging extends IncomingPacket {

    private byte status;
    private int posX;
    private int posY;
    private int posZ;
    private byte face;

    public PacketPlayInPlayerDigging() {
        super(ProtocolState.PLAY, 0x07);
    }

    @Override
    public void read(UnreadPacket packet) throws PacketException, IOException {
        this.status = packet.getStream().readByte();
        this.posX = packet.getStream().readInt();
        this.posY = packet.getStream().readUnsignedByte();
        this.posZ = packet.getStream().readInt();
        this.face = packet.getStream().readByte();
    }

    public byte getStatus() {
        return status;
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

    public byte getFace() {
        return face;
    }

}
