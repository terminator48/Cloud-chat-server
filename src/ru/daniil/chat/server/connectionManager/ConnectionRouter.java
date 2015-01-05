/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.daniil.chat.server.connectionManager;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import ru.daniil.chat.Configuration;
import ru.daniil.chat.server.connectionManager.packetEngine.AbstractBasicPacket;
import ru.daniil.chat.server.connectionManager.packetEngine.PacketOuterClass;
import ru.daniil.chat.server.connectionManager.packetEngine.PacketOuterClass.Packet;

import java.lang.reflect.Constructor;
import java.util.HashMap;

/**
 * @author Daniil
 */
public class ConnectionRouter {

    public static HashMap<String, ConnectedClient> clients = new HashMap<String, ConnectedClient>();
    public static HashMap<Integer, Constructor<?>> packets = new HashMap<Integer, Constructor<?>>();

    public static void routePacket(ChannelHandlerContext ctx, Object packet) {
        if (!clients.containsKey(getId(ctx))) {
            clients.put(getId(ctx), new ConnectedClient(ctx));
        }
        if (!(packet instanceof ByteBuf))
            return;
        ByteBuf data = (ByteBuf) packet;

        byte[] readBuffer = new byte[data.readableBytes()];
        ((ByteBuf) packet).readBytes(readBuffer);

        // PacketOuterClass.AbstractBasicPacket.Builder protoBuf = PacketOuterClass.AbstractBasicPacket.parseFrom(readBuffer);
        // int packetType = protoBuf.getType();
        // Class<ClientHandshakePacket> clientHandshakePacketClass = ClientHandshakePacket.class;
        // Constructor<?> constructor = clientHandshakePacketClass.getConstructors()[0];
        // constructor.newInstance(packetType, packetType, packetType);
    }

    public static String getId(ChannelHandlerContext ctx) {
        return ctx.channel().id().asLongText();
    }

    public static void disconnect(ChannelHandlerContext ctx) {
        if (!Configuration.supportReauth) {
            clients.remove(getId(ctx));
        } else {
            getClient(getId(ctx)).setState(ConnectedClient.ClientState.CLOSED);
        }
    }

    public static void registerPacket(AbstractBasicPacket packet){
        Constructor<?> constructor = packet.getClass().getConstructors()[0];
        packets.put(packet.getID(), constructor);
    }

    public static Constructor<?> getConstructorByPacketID(int id){
        if(!packets.containsKey(id))
            return null;
        return packets.get(id);
    }

    public static ConnectedClient getClient(String id) {
        return clients.get(id);
    }
}
