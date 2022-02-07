/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnTapThi.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;


/**
 *
 * @author Dell
 */
public class Server {
    public static void main(String[] args) throws IOException {
        System.out.println("UDP SERVER Started!!!");
        DatagramSocket channel = new DatagramSocket(6969);
        System.out.println("Waiting...");
        
        byte[] buffer = new byte[1024];
        DatagramPacket recPacket = new DatagramPacket(buffer, buffer.length);
        do{
            //Receive packet from clients
            channel.receive(recPacket);
            String recMessage = new String(recPacket.getData(), 0, recPacket.getLength());
            System.out.println("Received packet from " + recPacket.getSocketAddress() + ": " + recMessage);
            
            //Response to client information
            byte[] responseBuffer = new byte[1024];
            DatagramPacket responsePacket;
            switch(recMessage){
                case "time":
                    Date now = new Date();
                    responseBuffer = now.toString().getBytes();
                    responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length, recPacket.getSocketAddress());
                    channel.send(responsePacket);
                    break;
                case "USD":
                    responseBuffer = "24 VND = 1 USD".getBytes();
                    responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length, recPacket.getSocketAddress());
                    channel.send(responsePacket);
                    break;
                case "weather":
                    responseBuffer = "Rainning as f4ck!!!".getBytes();
                    responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length, recPacket.getSocketAddress());
                    channel.send(responsePacket);
                    break;
                default:
                    responseBuffer = "???".getBytes();
                    responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length, recPacket.getSocketAddress());
                    channel.send(responsePacket);
                    break;
            }
        }while(true);
    }
}
