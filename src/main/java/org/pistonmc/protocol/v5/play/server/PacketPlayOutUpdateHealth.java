package org.pistonmc.protocol.v5.play.server;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.OutgoingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.stream.PacketOutputStream;

import java.io.IOException;

public class PacketPlayOutUpdateHealth extends OutgoingPacket {

    private float health;
    private short food;
    private float saturation;

    public PacketPlayOutUpdateHealth(float health, short food, float saturation) {
        super(ProtocolState.PLAY, 0x06);
        this.health = health;
        this.food = food;
        this.saturation = saturation;
    }

    public float getHealth() {
        return health;
    }

    public short getFood() {
        return food;
    }

    public float getSaturation() {
        return saturation;
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        stream.writeFloat(health);
        stream.writeShort(food);
        stream.writeFloat(saturation);
    }

}
