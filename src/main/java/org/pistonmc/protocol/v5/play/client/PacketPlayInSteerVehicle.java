package org.pistonmc.protocol.v5.play.client;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.IncomingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.packet.UnreadPacket;

import java.io.IOException;

public class PacketPlayInSteerVehicle extends IncomingPacket {

    private float sideways;
    private float forward;
    private boolean jump;
    private boolean unmount;

    public PacketPlayInSteerVehicle() {
        super(ProtocolState.PLAY, 0x0C);
    }

    @Override
    public void read(UnreadPacket packet) throws PacketException, IOException {
        this.sideways = packet.getStream().readFloat();
        this.forward = packet.getStream().readFloat();
        this.jump = packet.getStream().readBoolean();
        this.unmount = packet.getStream().readBoolean();
    }

    public float getSideways() {
        return sideways;
    }

    public float getForward() {
        return forward;
    }

    public boolean isJump() {
        return jump;
    }

    public boolean isUnmount() {
        return unmount;
    }

}
