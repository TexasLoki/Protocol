package org.pistonmc.protocol.v5;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.plugin.protocol.Protocol;
import org.pistonmc.protocol.PlayerConnection;
import org.pistonmc.protocol.packet.Packet;
import org.pistonmc.protocol.v5.play.client.*;

public class StickyProtocol extends Protocol {

    public StickyProtocol() {
        super(5);
    }

    public StickyProtocol(int version, PlayerConnection connection) {
        super(version, connection);
    }

    @Override
    public void onEnable() {
	    add(new PacketPlayInKeepAlive());
	    add(new PacketPlayInChatMessage());
	    add(new PacketPlayInUseEntity());
	    add(new PacketPlayInPlayer());
	    add(new PacketPlayInPlayerPosition());
	    add(new PacketPlayInPlayerLook());
	    add(new PacketPlayInPlayerPositionAndLook());
	    add(new PacketPlayInPlayerDigging());

	    add(new PacketPlayInHeldItemChange());
	    add(new PacketPlayInAnimation());

	    add(new PacketPlayInSteerVehicle());
	    add(new PacketPlayInCloseWindow());

	    add(new PacketPlayInConfirmTransaction());

	    add(new PacketPlayInEnchantItem());
	    add(new PacketPlayInUpdateSign());
	    add(new PacketPlayInPlayerAbilities());
	    add(new PacketPlayInTabComplete());
    }

    @Override
    public void handle(Packet packet) throws PacketException {

    }

    @Override
    public Protocol create(PlayerConnection connection) {
        return new StickyProtocol(version, connection);
    }

}
