/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UDPChat;
import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;


/**
 *
 * @author Dell
 */
public class UDPServer {
    public static void main(String[] args) throws IOException {
        System.out.println("SERVER");
        DatagramSocket channel = new DatagramSocket(6969);
        System.out.println("Waiting...");
        byte[] buffer = new byte[1024];
        DatagramPacket packet, responsePacket;
        
        while(true){
            packet = new DatagramPacket(buffer, buffer.length);
            channel.receive(packet);
            
            String recMessage = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Received packet from " + packet.getSocketAddress() + ": " + recMessage);
            
            Date now = new Date();
            byte[] response = (now + ": " +recMessage).getBytes();
            responsePacket = new DatagramPacket(response, response.length, packet.getSocketAddress());
            channel.send(responsePacket);
            
        }
    }
}
