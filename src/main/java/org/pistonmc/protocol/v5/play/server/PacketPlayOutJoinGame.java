package org.pistonmc.protocol.v5.play.server;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.OutgoingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.stream.PacketOutputStream;

import java.io.IOException;

public class PacketPlayOutJoinGame extends OutgoingPacket {

    private int entity;
    private int gamemode;
    private byte dimension;
    private int difficulty;
    private int maxPlayers;
    private String level;

    public PacketPlayOutJoinGame(int entity, int gamemode, byte dimension, int difficulty, int maxPlayers, String level) {
        super(ProtocolState.PLAY, 0x01);
        this.entity = entity;
        this.gamemode = gamemode;
        this.dimension = dimension;
        this.difficulty = difficulty;
        this.maxPlayers = maxPlayers;
        this.level = level;
    }

    public int getEntity() {
        return entity;
    }

    public int getGamemode() {
        return gamemode;
    }

    public byte getDimension() {
        return dimension;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public String getLevel() {
        return level;
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        stream.writeInt(entity);
        stream.writeUnsignedByte(gamemode);
        stream.writeByte(dimension);
        stream.writeUnsignedByte(difficulty);
        stream.writeUnsignedByte(maxPlayers);
        stream.writeString(level);
    }

}
