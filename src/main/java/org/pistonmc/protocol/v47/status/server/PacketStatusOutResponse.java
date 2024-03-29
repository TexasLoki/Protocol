package org.pistonmc.protocol.v47.status.server;

import org.json.JSONObject;
import org.pistonmc.event.connection.ServerListPingEvent;
import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.OutgoingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.stream.PacketOutputStream;

import java.io.IOException;

public class PacketStatusOutResponse extends OutgoingPacket {

    private JSONObject json;

    public PacketStatusOutResponse(JSONObject json) {
        super(ProtocolState.STATUS, 0x00);
        this.json = json;
    }

    public PacketStatusOutResponse(ServerListPingEvent event) {
        this(event.serialize());
    }

    public JSONObject getJSON() {
        return json;
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        stream.writeJSON(json);
    }

}
