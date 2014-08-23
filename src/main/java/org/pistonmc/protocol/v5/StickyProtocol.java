package org.pistonmc.protocol.v5;

import org.pistonmc.protocol.Client;
import org.pistonmc.stickypiston.network.protocol.Protocol;

public class StickyProtocol extends Protocol {

    public StickyProtocol(Client client) {
        super(client);
    }

    @Override
    public Protocol create(Client client) {
        return new StickyProtocol(client);
    }

}
