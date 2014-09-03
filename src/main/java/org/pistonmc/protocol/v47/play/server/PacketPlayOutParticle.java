package org.pistonmc.protocol.v47.play.server;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.OutgoingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.stream.PacketOutputStream;

import java.io.IOException;

public class PacketPlayOutParticle extends OutgoingPacket {

    private String effect;
    private float x;
    private float y;
    private float z;
    private float xOffset;
    private float yOffset;
    private float zOffset;
    private float data;
    private int count;

    public PacketPlayOutParticle(String effect, float x, float y, float z, float xOffset, float yOffset, float zOffset, float data, int count) {
        super(ProtocolState.PLAY, 0x2A);
        this.effect = effect;
        this.x = x;
        this.y = y;
        this.z = z;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.zOffset = zOffset;
        this.data = data;
        this.count = count;
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        stream.writeString(effect);
        stream.writeFloat(x);
        stream.writeFloat(y);
        stream.writeFloat(z);
        stream.writeFloat(xOffset);
        stream.writeFloat(yOffset);
        stream.writeFloat(zOffset);
        stream.writeFloat(data);
        stream.writeInt(count);
    }

}