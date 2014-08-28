package org.pistonmc.protocol.v5.login.server;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.OutgoingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.stream.PacketOutputStream;

import java.io.IOException;

public class PacketLoginOutEncryptionRequest extends OutgoingPacket {

    private String sessionId;
    private byte[] publicKey;
    private byte[] verifyToken;

    public PacketLoginOutEncryptionRequest(String sessionId, byte[] publicKey, byte[] verifyToken) {
        super(ProtocolState.LOGIN, 0x01);
        this.sessionId = sessionId;
        this.publicKey = publicKey;
        this.verifyToken = verifyToken;
    }

    public String getSessionId() {
        return sessionId;
    }

    public byte[] getPublicKey() {
        return publicKey;
    }

    public byte[] getVerifyToken() {
        return verifyToken;
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        stream.writeString(sessionId);
        stream.writeByteArray(publicKey);
        stream.writeByteArray(verifyToken);
    }

}
