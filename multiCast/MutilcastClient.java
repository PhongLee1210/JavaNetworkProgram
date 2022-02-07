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

/**
 *
 * @author Dell
 */
public class MutilcastClient {
    public static void main(String[] args) {
        System.out.println("UDP Multicast Time Client Started");
        try{
            MulticastSocket multicastSocket = new MulticastSocket(9877);
            InetAddress address = InetAddress.getByName("224.7.7.7");
            multicastSocket.joinGroup(address);
            
            byte[] data = new byte[256];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            while(true){
               multicastSocket.receive(packet);
               String message = new String(packet.getData(), 0, packet.getLength());
               System.out.println("Message from " + packet.getAddress() + ":" +
                                    message);
            }
            
        }catch(IOException IoEx){
            IoEx.printStackTrace();
        }
        System.out.println("Done.");
    }
}
