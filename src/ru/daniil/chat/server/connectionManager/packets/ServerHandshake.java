/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.daniil.chat.server.connectionManager.packets;

import java.io.UnsupportedEncodingException;

import ru.daniil.chat.ChatServer;
import ru.daniil.chat.server.connectionManager.packetEngine.PacketOuterClass;
import ru.daniil.chat.server.connectionManager.packetEngine.ServerPacket;

/**
 *
 * @author Daniil
 */
public class ServerHandshake extends ServerPacket {

    @Override
    public String getName() {
        return "ServerHandshake";
    }

    @Override
    public int getID() {
        return 42;
    }


    @Override
    public PacketOuterClass.Packet getPacket() {
        PacketOuterClass.Packet.Builder poc = PacketOuterClass.Packet.newBuilder();
        poc.setEnc(0);
        poc.setType(this.getID());
        poc.setData(ChatServer.APIVersion+":"+ChatServer.ServerName);
        return poc.build();
    }
}
