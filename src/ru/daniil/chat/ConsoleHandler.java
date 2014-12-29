/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.daniil.chat;

import java.util.Map;
import ru.daniil.chat.server.MainServerController;

/**
 *
 * @author Daniil
 */
public class ConsoleHandler {

    public static void error(String msg) {
        System.err.println(msg);
    }

    public static void log(String msg) {
        System.out.println(msg);
    }

    public static void success(String msg) {
        // dumpAllStackTraces();
        System.out.println(msg);
    }

    public static void shutdown() {
        log("Got shutdown command");
        ChatServer.server.shutdownServer();
        success("Application is now can be halted!");
        success("Good bye!");
        System.exit(1);
    }

    public static void conosleCommand(String cmd) {

        if (cmd.trim().equalsIgnoreCase("stop")) {
            shutdown();
        } else {
            error("Unknown command");
        }
    }

    public static void dumpAllStackTraces() {
        for (Map.Entry<Thread, StackTraceElement[]> entry
                : Thread.getAllStackTraces().entrySet()) {
            System.out.println(entry.getKey().getName() + ":");
            for (StackTraceElement element : entry.getValue()) {
                error("\t" + element);
            }
        }
    }
}
