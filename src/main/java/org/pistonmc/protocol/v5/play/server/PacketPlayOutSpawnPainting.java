package org.pistonmc.protocol.v5.play.server;

import org.pistonmc.data.Direction;
import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.stream.PacketOutputStream;
import org.pistonmc.protocol.v5.play.server.groups.PacketPlayOutEntity;

import java.io.IOException;

public class PacketPlayOutSpawnPainting extends PacketPlayOutEntity {

    private String title;
    private int x;
    private int y;
    private int z;
    private Direction direction;

    public PacketPlayOutSpawnPainting(int entity, String title, int x, int y, int z, Direction direction) {
        super(0x10, entity);
        this.title = title;
        this.x = x;
        this.y = y;
        this.z = z;
        this.direction = direction;
    }

    public String getTitle() {
        return title;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        super.write(stream);
        stream.writeString(title);
        stream.writeInt(x);
        stream.writeInt(y);
        stream.writeInt(z);
        stream.writeInt(direction.getId());
    }

}
