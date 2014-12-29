/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.daniil.chat.server.connectionManager;

import io.netty.channel.ChannelHandlerContext;
import java.util.HashMap;
import ru.daniil.chat.Configuration;

/**
 *
 * @author Daniil
 */
public class ConnectionRouter {

    public static HashMap<String, ConnectedClient> clients = new HashMap<String, ConnectedClient>();

    public static void routePacket(ChannelHandlerContext ctx, Object packet) {
        if(!clients.containsKey(ctx.channel().id().asLongText())){
            clients.put(ctx.channel().id().asLongText(), new ConnectedClient(ctx));
        }
    }

    public static void disconnect(ChannelHandlerContext ctx) {
        if(!Configuration.supportReauth){
            clients.remove(ctx.channel().id().asLongText());
        }

    }
}
