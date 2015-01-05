/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.daniil.chat;

import java.util.Scanner;
import static ru.daniil.chat.ConsoleHandler.log;
import ru.daniil.chat.server.MainServerController;
import ru.daniil.chat.server.connectionManager.ConnectionRouter;
import ru.daniil.chat.server.connectionManager.packets.ClientHandshakePacket;
import ru.daniil.chat.server.connectionManager.packets.ServerError;
import ru.daniil.chat.server.connectionManager.packets.ServerFatalError;
import ru.daniil.chat.server.connectionManager.packets.ServerHandshake;

/**
 *
 * @author Daniil
 */
public class ChatServer {

    public static String APIVersion = "0.1";
    public static String ServerName = "RU1";
    public static MainServerController server = new MainServerController();

    public static void main(String[] args) throws Exception {
        ConnectionRouter.registerPacket(new ServerError());
        ConnectionRouter.registerPacket(new ServerFatalError());
        ConnectionRouter.registerPacket(new ServerHandshake());
        ConnectionRouter.registerPacket(new ClientHandshakePacket(0, null, null));


        server.start();
        Scanner sc = new Scanner(System.in);
        ConsoleHandler.log("Listening to commands");
        
        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            ConsoleHandler.conosleCommand(command);
        }
    }

}
