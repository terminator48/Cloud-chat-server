/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.daniil.chat.server.connectionManager.packets;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ru.daniil.chat.ConsoleHandler.error;

import ru.daniil.chat.server.connectionManager.ConnectedClient;
import ru.daniil.chat.server.connectionManager.ConnectionRouter;
import ru.daniil.chat.server.connectionManager.packetEngine.ClientPacket;

/**
 * @author Daniil
 */
public class ClientHandshakePacket extends ClientPacket {

    public ClientHandshakePacket(int crypt, String data, String clientId) {
        super(crypt, data, clientId);
    }

    @Override
    public void process() {
        ConnectedClient cc = ConnectionRouter.getClient(clientId);
        if (cc != null) {
            if (cc.getState() == ConnectedClient.ClientState.CONNECTED || cc.getState() == ConnectedClient.ClientState.CONNECTED) {
                String[] dataProtocol = data.split(":");
                if (dataProtocol.length == 3) {
                    cc.APIVersion = dataProtocol[0];
                    cc.DeviceID = dataProtocol[1];
                    cc.DeviceName = dataProtocol[2];
                    cc.setState(ConnectedClient.ClientState.HANDSHAKED);
                    cc.sendPacket(new ServerHandshake());
                }
            }
        } else error("Dropped packet ClientHandshakePacket: no clients found");
    }

    @Override
    public String getName() {
        return "ClientHandshakePacket";
    }

    @Override
    public int getID() {
        return 0;
    }
}
