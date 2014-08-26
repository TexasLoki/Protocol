package org.pistonmc.protocol.v5.play.server;

import org.json.JSONObject;
import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.OutgoingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.stream.PacketOutputStream;

import java.io.IOException;

public class PlayPlayOutChatMessage extends OutgoingPacket {

    private JSONObject json;

    public PlayPlayOutChatMessage(JSONObject json) {
        super(ProtocolState.PLAY, 0x00);
        this.json = json;
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        stream.writeString(json.toString(4));
    }

}
