/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.daniil.chat.server.connectionManager.packetEngine;

/**
 * @author Daniil
 */
public abstract class AbstractBasicPacket {
    public abstract String getName();

    public abstract int getID();

}
