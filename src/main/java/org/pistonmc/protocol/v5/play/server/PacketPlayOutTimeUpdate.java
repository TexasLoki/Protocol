package org.pistonmc.protocol.v5.play.server;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.OutgoingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.stream.PacketOutputStream;

import java.io.IOException;

public class PacketPlayOutTimeUpdate extends OutgoingPacket {

    private long age;
    private long time;

    public PacketPlayOutTimeUpdate(long age, long time) {
        super(ProtocolState.PLAY, 0x03);
        this.age = age;
        this.time = time;
    }

    public long getAge() {
        return age;
    }

    public long getTime() {
        return time;
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        stream.writeLong(age);
        stream.writeLong(time);
    }

}
