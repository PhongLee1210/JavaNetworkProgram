/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class Server {
    public static void main(String[] args) throws SocketException {
        System.out.println("SERVER");
        DatagramSocket server = new DatagramSocket(6969);
        System.out.println("Waiting...");
        
        responseToClients client = new responseToClients(server);
        Thread clientThread = new Thread(client);
        clientThread.start();

    }
}
class responseToClients implements Runnable{
    private DatagramSocket channel;
    
    responseToClients(DatagramSocket channel){
        this.channel = channel;
    }
    
    @Override
    public void run() {
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        
        do{
            try {
                byte[] response = new byte[256];
                DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
                
                channel.receive(packet);
                byte[] recData = packet.getData();
                String recMessage = new String(recData, StandardCharsets.UTF_8);
                System.out.println("Received packet from " + packet.getSocketAddress() + ": " + recMessage);
                
                switch(recMessage) {
                    case "time":   
                        response = "Time".getBytes();
                        responsePacket = new DatagramPacket(response, response.length, packet.getSocketAddress());
                        channel.send(responsePacket);
                      break;
                      
                    case "covid":   
                        response = "Alot get sick, wear your mask!!!".getBytes();
                        responsePacket = new DatagramPacket(response, response.length, packet.getSocketAddress());
                        channel.send(responsePacket);
                      break;
                      
                    case "weather":   
                        response = "Alot get sick, wear your mask!!!".getBytes();
                        responsePacket = new DatagramPacket(response, response.length, packet.getSocketAddress());
                        channel.send(responsePacket);
                      break;
                      
                    case "gold price":   
                        response = "Alot get sick, wear your mask!!!".getBytes();
                        responsePacket = new DatagramPacket(response, response.length, packet.getSocketAddress());
                        channel.send(responsePacket);
                      break;
                   
                    case "q":   
                        response = "bye!!!".getBytes();
                        responsePacket = new DatagramPacket(response, response.length, packet.getSocketAddress());
                        channel.send(responsePacket);
                      break;
                  }
            } catch (IOException ioEx){
                System.err.println("I/O error: " + ioEx.getMessage());
            }
            Thread.yield();
        }while(true);
    }
}
