package ru.daniil.chat.server.connectionManager.packets;

import ru.daniil.chat.server.connectionManager.packetEngine.PacketOuterClass;
import ru.daniil.chat.server.connectionManager.packetEngine.ServerPacket;

/**
 * Created by Daniil on 31.12.2014.
 */
public class ServerFatalError extends ServerPacket {
    @Override
    public PacketOuterClass.Packet getPacket() {
        PacketOuterClass.Packet.Builder poc = PacketOuterClass.Packet.newBuilder();
        poc.setEnc(0);
        poc.setType(this.getID());
        return poc.build();
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getID() {
        return 41;
    }
}
