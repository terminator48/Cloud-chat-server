/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.daniil.chat;

import java.util.Scanner;
import static ru.daniil.chat.ConsoleHandler.log;
import ru.daniil.chat.server.MainServerController;

/**
 *
 * @author Daniil
 */
public class ChatServer {

    public static MainServerController server = new MainServerController();

    public static void main(String[] args) throws Exception {
        server.start();
        Scanner sc = new Scanner(System.in);
        ConsoleHandler.log("Listening to commands");
        
        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            ConsoleHandler.conosleCommand(command);
        }
    }

}
