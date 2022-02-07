/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class ClientChannel {
    public static void main(String[] args) throws IOException{
        InetSocketAddress address = new InetSocketAddress("localhost", 6969);
        SocketChannel channel = SocketChannel.open(address);
        System.out.println("Connect Susccess!!!");
        
        //Send message Thread
        SenderThread sender = new SenderThread(channel);
        Thread senderThread = new Thread(sender);
        senderThread.start();
        
        //Receive message Thread
        ReceiverThread receiver = new ReceiverThread(channel);
        Thread receiverThread = new Thread(receiver);
        receiverThread.start();
    }
}
