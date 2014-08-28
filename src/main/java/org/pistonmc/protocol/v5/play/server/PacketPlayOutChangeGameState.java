package org.pistonmc.protocol.v5.play.server;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.OutgoingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.stream.PacketOutputStream;

import java.io.IOException;

public class PacketPlayOutChangeGameState extends OutgoingPacket {

    private byte reason;
    private float value;

    public PacketPlayOutChangeGameState(byte reason, float value) {
        super(ProtocolState.PLAY, 0x2B);
        this.reason = reason;
        this.value = value;
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        stream.writeUnsignedByte(reason);
        stream.writeFloat(value);
    }

}