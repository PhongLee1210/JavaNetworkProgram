/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThreadChat;

import java.io.IOException;
import static java.lang.Thread.MAX_PRIORITY;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class Client {
    public static void main(String[] args) throws UnknownHostException, IOException {
        InetSocketAddress address = new InetSocketAddress("localhost", 6969);
        SocketChannel channel = SocketChannel.open(address);
        System.out.println("Connect Susccess!!!");
        
        //Send message Thread
        chatServer chat = new chatServer(channel);
        Thread chatThread = new Thread(chat);
        chatThread.setDaemon(true);
        chatThread.start();
        
        //Receive message Thread
        String message = "";
        do{
            message = ControllerMethod.receiveMessage(channel);
            System.out.println("SERVER> " + message);
        }while(!message.equals("q"));
        channel.close();
        System.out.println("Done.");

    }
}
