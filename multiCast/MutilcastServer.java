/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiCast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Date;

/**
 *
 * @author Dell
 */
public class MutilcastServer {
    public static void main(String[] args) {
        System.out.println("UDP Multicast Time Server started");
        try{
            MulticastSocket multicastSocket = new MulticastSocket();
            InetAddress address = InetAddress.getByName("224.7.7.7");
            multicastSocket.joinGroup(address);
            
            DatagramPacket packet;
            while(true){
                Thread.sleep(2000);
                String message = (new Date()).toString();
                System.out.println("Sending: " + message);
                byte[] data = message.getBytes();
                packet = new DatagramPacket(data, data.length,
                                            address, 9877);
                multicastSocket.send(packet);
            }
        
        }catch(IOException | InterruptedException ex){
            ex.printStackTrace();
        }
        System.out.println("Done.");
    }
}
