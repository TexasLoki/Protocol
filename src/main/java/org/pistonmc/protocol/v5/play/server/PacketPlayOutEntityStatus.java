package org.pistonmc.protocol.v5.play.server;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.stream.PacketOutputStream;
import org.pistonmc.protocol.v5.play.server.groups.PacketPlayOutEntity;

import java.io.IOException;

public class PacketPlayOutEntityStatus extends PacketPlayOutEntity {

    private int status;

    public PacketPlayOutEntityStatus(int entity, int status) {
        super(0x1A, entity);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        super.write(stream);
        stream.writeByte(status);
    }

}
