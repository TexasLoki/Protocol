package org.pistonmc.protocol.v5.play.server;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.stream.PacketOutputStream;
import org.pistonmc.protocol.packet.OutgoingPacket;

import java.io.IOException;

public class PacketPlayOutEffect extends OutgoingPacket {

    private int effect;
    private int x;
    private byte y;
    private int z;
    private int data;
    private boolean disableRelativeVolume;

    public PacketPlayOutEffect(int effect, int x, byte y, int z, int data, boolean disableRelativeVolume) {
        super(ProtocolState.PLAY, 0x28);
        this.effect = effect;
        this.x = x;
        this.y = y;
        this.z = z;
        this.data = data;
        this.disableRelativeVolume = disableRelativeVolume;
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        stream.writeInt(effect);
        stream.writeInt(x);
        stream.writeByte(y);
        stream.writeInt(z);
        stream.writeInt(data);
        stream.writeBoolean(disableRelativeVolume);
    }

}