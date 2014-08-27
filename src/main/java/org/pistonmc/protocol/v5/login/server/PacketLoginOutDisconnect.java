package org.pistonmc.protocol.v5.login.server;

import org.json.JSONObject;
import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.OutgoingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.stream.PacketOutputStream;
import org.pistonmc.util.ChatFormatter;

import java.io.IOException;

public class PacketLoginOutDisconnect extends OutgoingPacket {

    private JSONObject json;

    public PacketLoginOutDisconnect(JSONObject json) {
        super(ProtocolState.LOGIN, 0x00);
        this.json = json;
    }

    public PacketLoginOutDisconnect(String message) {
        this(ChatFormatter.serialize(message));
    }

    public JSONObject getJSON() {
        return json;
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        stream.writeJSON(json);
    }

}
