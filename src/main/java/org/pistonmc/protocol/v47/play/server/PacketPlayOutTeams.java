package org.pistonmc.protocol.v47.play.server;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.OutgoingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.stream.PacketOutputStream;

import java.io.IOException;

public class PacketPlayOutTeams extends OutgoingPacket {

    private String teamName;
    private byte mode;
    private String displayName;
    private String prefix;
    private String suffix;
    private byte friendlyFire;
    private String[] players;

    public PacketPlayOutTeams(String teamName, byte mode, String displayName, String prefix, String suffix,
                              byte friendlyFire, String[] players) {
        super(ProtocolState.PLAY, 0x3E);
        this.teamName = teamName;
        this.mode = mode;
        this.displayName = displayName;
        this.prefix = prefix;
        this.suffix = suffix;
        this.friendlyFire = friendlyFire;
        this.players = players;
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        stream.writeString(teamName);
        stream.writeByte(mode);
        stream.writeString(displayName);
        stream.writeString(prefix);
        stream.writeString(suffix);
        stream.writeByte(friendlyFire);
        stream.writeStringArray(players);
    }

    public String getTeamName() {
        return teamName;
    }

    public byte getMode() {
        return mode;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public byte getFriendlyFire() {
        return friendlyFire;
    }

    public String[] getPlayers() {
        return players;
    }

}