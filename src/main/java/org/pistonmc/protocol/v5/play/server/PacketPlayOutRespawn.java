package org.pistonmc.protocol.v5.play.server;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.OutgoingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.stream.PacketOutputStream;

import java.io.IOException;

public class PacketPlayOutRespawn extends OutgoingPacket {

    private int dimension;
    private int difficulty;
    private int gamemode;
    private String level;

    public PacketPlayOutRespawn(int dimension, int difficulty, int gamemode, String level) {
        super(ProtocolState.PLAY, 0x07);
        this.dimension = dimension;
        this.difficulty = difficulty;
        this.gamemode = gamemode;
        this.level = level;
    }

    public int getDimension() {
        return dimension;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getGamemode() {
        return gamemode;
    }

    public String getLevel() {
        return level;
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        stream.writeInt(dimension);
        stream.writeUnsignedByte(difficulty);
        stream.writeUnsignedByte(gamemode);
        stream.writeString(level);
    }

}
