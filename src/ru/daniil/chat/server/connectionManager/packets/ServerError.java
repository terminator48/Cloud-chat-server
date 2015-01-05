/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.daniil.chat.server.connectionManager.packets;

import ru.daniil.chat.server.connectionManager.packetEngine.PacketOuterClass;
import ru.daniil.chat.server.connectionManager.packetEngine.ServerPacket;

import static ru.daniil.chat.server.connectionManager.packetEngine.PacketOuterClass.*;

/**
 * @author Daniil
 */
public class ServerError extends ServerPacket {

    @Override
    public Packet getPacket() {
        Packet.Builder poc = Packet.newBuilder();
        poc.setEnc(0);
        poc.setType(this.getID());
        return poc.build();
    }

    @Override
    public String getName() {
        return "ServerErrorPacket";
    }

    @Override
    public int getID() {
        return 40;
    }

}
