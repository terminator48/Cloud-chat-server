/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.daniil.chat.server.connectionManager;

import io.netty.channel.ChannelHandlerContext;
import ru.daniil.chat.server.connectionManager.packetEngine.ServerPacket;

/**
 *
 * @author Daniil
 */
public class ConnectedClient {

    public enum ClientState {

        CONNECTED, HANDSHAKED, AUTHENTIFICATED, CLOSED
    }
    private ChannelHandlerContext ctx;
    private String protocolVersion;
    private ClientState state;

    public String APIVersion;
    public String DeviceID;
    public String DeviceName;

    public ConnectedClient(ChannelHandlerContext ctx) {
        this.ctx = ctx;
        this.state = ClientState.CONNECTED;
    }

    public ClientState getState() {
        test(1);
        return this.state;
    }
    

    public void setState(ClientState cs) {
        this.state = cs;
        
    }

    public void sendPacket(ServerPacket packet) {
        byte[] packetBytes = packet.asBytes();
        if (packetBytes != null) {
            ctx.write(packet.asBytes());
        } else if (!packet.getName().equals("ServerErrorPacket")) {
            ctx.write(packet.asBytes());
        }
    }
}
