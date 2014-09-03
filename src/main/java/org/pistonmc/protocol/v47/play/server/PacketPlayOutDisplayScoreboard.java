package org.pistonmc.protocol.v47.play.server;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.OutgoingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.stream.PacketOutputStream;

import java.io.IOException;

public class PacketPlayOutDisplayScoreboard extends OutgoingPacket {

    private byte position;
    private String boardName;

    public PacketPlayOutDisplayScoreboard(byte position, String boardName) {
        super(ProtocolState.PLAY, 0x3D);
        this.position = position;
        this.boardName = boardName;
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        stream.writeByte(position);
        stream.writeString(boardName);
    }

    public byte getPosition() {
        return position;
    }

    public String getBoardName() {
        return boardName;
    }

}