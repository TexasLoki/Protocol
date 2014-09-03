package org.pistonmc.protocol.v47.login.client;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.packet.UnreadPacket;
import org.pistonmc.protocol.packet.IncomingPacket;

import java.io.IOException;

public class PacketLoginInEncryptionResponse extends IncomingPacket {

    private byte[] sharedSecret;
    private byte[] verifyToken;


    public PacketLoginInEncryptionResponse() {
        super(ProtocolState.LOGIN, 0x01);
    }

    @Override
    public void read(UnreadPacket packet) throws PacketException, IOException {
        this.sharedSecret = packet.getStream().readBytes();
        this.verifyToken = packet.getStream().readBytes();
    }

    public byte[] getSharedSecret() {
        return sharedSecret;
    }

    public byte[] getVerifyToken() {
        return verifyToken;
    }

}