/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.daniil.chat.server.connectionManager.packetEngine;

import ru.daniil.chat.server.connectionManager.packetEngine.PacketOuterClass.Packet;

/**
 *
 * @author Daniil
 */
public abstract class ServerPacket extends AbstractBasicPacket {
    public abstract Packet getPacket();
}
