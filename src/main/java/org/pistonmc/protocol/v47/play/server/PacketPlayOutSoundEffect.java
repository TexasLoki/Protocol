package org.pistonmc.protocol.v47.play.server;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.OutgoingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.stream.PacketOutputStream;

import java.io.IOException;

public class PacketPlayOutSoundEffect extends OutgoingPacket {

    private int sound;
    private int x;
    private int y;
    private int z;
    private float volume;
    private byte pitch;

    public PacketPlayOutSoundEffect(int sound, int x, byte y, int z, float volume, byte pitch) {
        super(ProtocolState.PLAY, 0x29);
        this.sound = sound;
        this.x = x;
        this.y = y;
        this.z = z;
        this.volume = volume;
        this.pitch = pitch;
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        stream.writeInt(sound);
        stream.writeInt(x);
        stream.writeInt(y);
        stream.writeInt(z);
        stream.writeFloat(volume);
        stream.writeUnsignedByte(pitch);
    }

}