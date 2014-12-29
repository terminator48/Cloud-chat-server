/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.daniil.chat.server.connectionManager.packets;

/**
 *
 * @author Daniil
 */
public abstract class Packet {
    public enum PacketSide{
        SERVER, CLIENT
    }
    private Byte[] cryptMethod;
    private Byte[] data;
    private String clientId;
    
    public Packet(Byte[] data, Byte[] crypt, String clientId){
        this.data = data;
        this.cryptMethod = crypt;
        this.clientId = clientId;
    }
    
    public abstract void process();
    public abstract Byte[] asBytes();
    public abstract String getName();
    public abstract PacketSide getType();
    
}
