/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.daniil.chat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import ru.daniil.chat.ConsoleHandler;
import static ru.daniil.chat.ConsoleHandler.log;
import static ru.daniil.chat.ConsoleHandler.success;

/**
 *
 * @author Daniil
 */
public class MainServerController extends Thread {

    public final boolean SSL = System.getProperty("ssl") != null;
    public final int PORT = Integer.parseInt(System.getProperty("port", "8007"));
    public EventLoopGroup bossGroup = new NioEventLoopGroup(1);
    public EventLoopGroup workerGroup = new NioEventLoopGroup();
    public boolean active = true;

    public void run() {

        // Configure the server.
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            //p.addLast(new LoggingHandler(LogLevel.INFO));
                            p.addLast(new ServerChanelHandler());
                        }
                    });

            // Start the server.
            ChannelFuture f = b.bind(PORT).sync();

            // Wait until the server socket is closed.
            f.channel().closeFuture().sync();
        } catch (Exception ex) {
            ConsoleHandler.error(ex.getMessage());
        } finally {
            if (active) {
                shutdownServer();
            }
        }
    }

    public void shutdownServer() {
        if (active) {
            active = false;
            log("Shutting down internal server");
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            success("Shutting down successeful");
        }

    }

}
