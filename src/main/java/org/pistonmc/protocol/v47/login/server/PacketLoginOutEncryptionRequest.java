package org.pistonmc.protocol.v47.login.server;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.OutgoingPacket;
import org.pistonmc.protocol.packet.ProtocolState;
import org.pistonmc.protocol.stream.PacketOutputStream;

import java.io.IOException;

public class PacketLoginOutEncryptionRequest extends OutgoingPacket {

    private String serverId;
    private byte[] publicKey;
    private byte[] verifyToken;

    public PacketLoginOutEncryptionRequest(String serverId, byte[] publicKey, byte[] verifyToken) {
        super(ProtocolState.LOGIN, 0x01);
        this.serverId = serverId;
        this.publicKey = publicKey;
        this.verifyToken = verifyToken;
    }

    public String getSessionId() {
        return serverId;
    }

    public byte[] getPublicKey() {
        return publicKey;
    }

    public byte[] getVerifyToken() {
        return verifyToken;
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException, IOException {
        stream.writeString(serverId);
        stream.writeByteArray(publicKey);
        stream.writeByteArray(verifyToken);
    }

}
