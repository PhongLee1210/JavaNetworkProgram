/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UDPChat;

import java.io.IOException;
import java.net.*;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class UDPClient {
    public static void main(String[] args) throws IOException{
        System.out.println("CLIENT");
        DatagramSocket channel = new DatagramSocket();
        SocketAddress serverAddress = new InetSocketAddress("localhost", 6969);
        
        Scanner input = new Scanner(System.in);
        String message = "", recMessage;
        DatagramPacket packet, recPacket;
        
        do{
            System.out.print("Send: ");   message = input.nextLine();
            
            byte[] buffer = message.getBytes();
            packet = new DatagramPacket(buffer, buffer.length, serverAddress);
            channel.send(packet);
            
            byte[] recBuffer = new byte[1024];
            recPacket = new DatagramPacket(recBuffer, recBuffer.length);
            channel.receive(recPacket);
            
            recMessage = new String(recPacket.getData(), 0, recPacket.getLength());
            System.out.println("Echo - "+ recMessage);
            
            if(message.equals("q")) break;
        }while(true);
        System.out.println("Done.");
    }
}
