/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.daniil.chat.server.connectionManager.packetEngine;

/**
 *
 * @author Daniil
 */
public abstract class ClientPacket extends AbstractBasicPacket {

    protected int cryptMethod;
    protected String data;
    protected String clientId;

    public ClientPacket(int crypt, String data, String clientId) {
        this.data = data;
        this.cryptMethod = crypt;
        this.clientId = clientId;
    }

    public abstract void process();

}
