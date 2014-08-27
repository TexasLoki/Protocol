package org.pistonmc.protocol.v5;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.plugin.protocol.Protocol;
import org.pistonmc.protocol.PlayerConnection;
import org.pistonmc.protocol.packet.Packet;
import org.pistonmc.protocol.v5.play.client.PacketPlayInAnimation;
import org.pistonmc.protocol.v5.play.client.PacketPlayInChatMessage;
import org.pistonmc.protocol.v5.play.client.PacketPlayInClientSettings;
import org.pistonmc.protocol.v5.play.client.PacketPlayInCloseWindow;
import org.pistonmc.protocol.v5.play.client.PacketPlayInConfirmTransaction;
import org.pistonmc.protocol.v5.play.client.PacketPlayInEnchantItem;
import org.pistonmc.protocol.v5.play.client.PacketPlayInHeldItemChange;
import org.pistonmc.protocol.v5.play.client.PacketPlayInKeepAlive;
import org.pistonmc.protocol.v5.play.client.PacketPlayInPlayer;
import org.pistonmc.protocol.v5.play.client.PacketPlayInPlayerAbilities;
import org.pistonmc.protocol.v5.play.client.PacketPlayInPlayerDigging;
import org.pistonmc.protocol.v5.play.client.PacketPlayInPlayerLook;
import org.pistonmc.protocol.v5.play.client.PacketPlayInPlayerPosition;
import org.pistonmc.protocol.v5.play.client.PacketPlayInPlayerPositionAndLook;
import org.pistonmc.protocol.v5.play.client.PacketPlayInPluginMessage;
import org.pistonmc.protocol.v5.play.client.PacketPlayInSteerVehicle;
import org.pistonmc.protocol.v5.play.client.PacketPlayInTabComplete;
import org.pistonmc.protocol.v5.play.client.PacketPlayInUpdateSign;
import org.pistonmc.protocol.v5.play.client.PacketPlayInUseEntity;

public class StickyProtocol extends Protocol {

    public StickyProtocol() {
        super(5);
    }

    public StickyProtocol(int version, PlayerConnection connection) {
        super(version, connection);
    }

    @Override
    public void onLoad() {
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
        add(new PacketPlayInClientSettings());

        add(new PacketPlayInPluginMessage());
    }

    @Override
    public void handle(Packet packet) throws PacketException {

    }

    @Override
    public Protocol create(PlayerConnection connection) {
        return new StickyProtocol(version, connection);
    }

}
