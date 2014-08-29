package org.pistonmc.protocol.v5.login.client;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.packet.UnreadPacket;
import org.pistonmc.protocol.packet.IncomingPacket;

import java.io.IOException;

public class PacketLoginInEncryptionResponse extends IncomingPacket {

    private short sharedSecretLength;
    private byte[] sharedSecret;

    private short verifyTokenLength;
    private byte[] verifyToken;


    public PacketLoginInEncryptionResponse() {
        super(ProtocolState.LOGIN, 0x01);
    }

    @Override
    public void read(UnreadPacket packet) throws PacketException, IOException {
        sharedSecretLength = packet.getStream().readShort();
        sharedSecret = packet.getStream().readBytes();

        verifyTokenLength = packet.getStream().readShort();
        verifyToken = packet.getStream().readBytes();
    }

    public short getSharedSecretLength() {
        return sharedSecretLength;
    }

    public byte[] getSharedSecret() {
        return sharedSecret;
    }

    public short getVerifyTokenLength() {
        return verifyTokenLength;
    }

    public byte[] getVerifyToken() {
        return verifyToken;
    }

}