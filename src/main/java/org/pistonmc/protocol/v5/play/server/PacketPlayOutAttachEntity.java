package org.pistonmc.protocol.v5.play.server;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.stream.PacketOutputStream;
import org.pistonmc.protocol.v5.play.server.groups.PacketPlayOutEntity;

import java.io.IOException;

public class PacketPlayOutAttachEntity extends PacketPlayOutEntity {

    private int vehicle;
    private boolean leash;

    public PacketPlayOutAttachEntity(int entity, int vehicle, boolean leash) {
        super(0x1B, entity);
        this.vehicle = vehicle;
        this.leash = leash;
    }

    public int getVehicle() {
        return vehicle;
    }

    public boolean isLeash() {
        return leash;
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        super.write(stream);
        stream.writeInt(vehicle);
        stream.writeBoolean(leash);
    }

}
