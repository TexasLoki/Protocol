package org.pistonmc.protocol.v5.play.client;

import org.pistonmc.data.Direction;
import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.inventory.ItemStack;
import org.pistonmc.protocol.packet.IncomingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.packet.UnreadPacket;

import java.io.IOException;

public class PacketPlayInPlayerBlockPlacement extends IncomingPacket {

    private int posX;
    private int posY;
    private int posZ;
    private Direction direction;
    private ItemStack heldItem;
    private byte cursorX;
    private byte cursorY;
    private byte cursorZ;

    public PacketPlayInPlayerBlockPlacement() {
        super(ProtocolState.PLAY, 0x08);
    }

    @Override
    public void read(UnreadPacket packet) throws PacketException, IOException {
        this.posX = packet.getStream().readInt();
        this.posY = packet.getStream().readUnsignedByte();
        this.posZ = packet.getStream().readInt();
        this.direction = Direction.valueOf(packet.getStream().readByte());
        this.heldItem = packet.getStream().readItemStack();
        this.cursorX = packet.getStream().readByte();
        this.cursorY = packet.getStream().readByte();
        this.cursorZ = packet.getStream().readByte();
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getPosZ() {
        return posZ;
    }

    public Direction getDirection() {
        return direction;
    }

    public ItemStack getHeldItem() {
        return heldItem;
    }

    public byte getCursorX() {
        return cursorX;
    }

    public byte getCursorY() {
        return cursorY;
    }

    public byte getCursorZ() {
        return cursorZ;
    }

}