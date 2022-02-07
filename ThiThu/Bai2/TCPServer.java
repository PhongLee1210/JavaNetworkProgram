/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThiThu.Bai2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 *
 * @author Dell
 */
public class TCPServer {
    public static void main(String[] args) throws IOException {
        System.out.println("TCP Server started!!!");
        InetSocketAddress address = new InetSocketAddress(6969);
        ServerSocketChannel server = ServerSocketChannel.open();
        server.bind(address);
        
        SocketChannel channel = server.accept();
        //Receive message from client
        String message = "";
        message = ControllerMethod.receiveMessage(channel);
        System.out.println("CLIENT> " + message);
        
        //Send back message after process
        reverseMessage(channel, message);
        System.out.println("Done.");
        
    }
    public static void uppercaseMessage(SocketChannel channel,String message) throws IOException{
        String newMessage = message.toUpperCase();
        ControllerMethod.sendMessage(channel, newMessage);
    }
    public static void reverseMessage(SocketChannel channel,String message) throws IOException{
        String newMessage = "";
        for (int i = message.length() - 1; i >= 0; i--)
            newMessage += message.charAt(i);
        ControllerMethod.sendMessage(channel, newMessage);
    }
}
