package org.pistonmc.protocol.v5;

import org.pistonmc.plugin.protocol.Protocol;
import org.pistonmc.protocol.Client;

public class StickyProtocol extends Protocol {

    public StickyProtocol() {
        this(null);
    }

    public StickyProtocol(Client client) {
        super(client);
    }

    @Override
    public Protocol create(Client client) {
        return new StickyProtocol(client);
    }

}
