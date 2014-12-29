/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.daniil.chat.server.connectionManager;

import io.netty.channel.ChannelHandlerContext;

/**
 *
 * @author Daniil
 */
public class ConnectedClient {
    private ChannelHandlerContext ctx;
    
    public ConnectedClient(ChannelHandlerContext ctx){
        this.ctx = ctx;
    }
}
