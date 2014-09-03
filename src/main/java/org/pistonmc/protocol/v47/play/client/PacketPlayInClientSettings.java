package org.pistonmc.protocol.v47.play.client;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.IncomingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.packet.UnreadPacket;

import java.io.IOException;

public class PacketPlayInClientSettings extends IncomingPacket {

    private String locale;
    private byte viewDistance;
    private byte chatFlags;
    private boolean chatColours;
    private byte difficulty;
    private boolean showCape;

    public PacketPlayInClientSettings() {
        super(ProtocolState.PLAY, 0x15);
    }

    @Override
    public void read(UnreadPacket packet) throws PacketException, IOException {
        this.locale = packet.getStream().readString();
        this.viewDistance = packet.getStream().readByte();
        this.chatFlags = packet.getStream().readByte();
        this.chatColours = packet.getStream().readBoolean();
        this.difficulty = packet.getStream().readByte();
        this.showCape = packet.getStream().readBoolean();
    }

    public String getLocale() {
        return locale;
    }

    public byte getViewDistance() {
        return viewDistance;
    }

    public byte getChatFlags() {
        return chatFlags;
    }

    public boolean isChatColours() {
        return chatColours;
    }

    public byte getDifficulty() {
        return difficulty;
    }

    public boolean isShowCape() {
        return showCape;
    }
}
