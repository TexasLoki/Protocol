package org.pistonmc.protocol.v5.play.server;

import org.json.JSONObject;
import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.OutgoingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.stream.PacketOutputStream;

import java.io.IOException;

public class PacketPlayOutChatMessage extends OutgoingPacket {

    private JSONObject json;

    public PacketPlayOutChatMessage(JSONObject json) {
        super(ProtocolState.PLAY, 0x02);
        this.json = json;
    }

    public JSONObject getJSON() {
        return json;
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        stream.writeString(json.toString(4));
    }

}
