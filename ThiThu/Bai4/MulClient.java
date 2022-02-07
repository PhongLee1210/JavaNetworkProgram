/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThiThu.Bai4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;


/**
 *
 * @author Dell
 */
public class MulClient {
    public static void main(String[] args) throws IOException {
        System.out.println("Multicast Client started!!!");
        MulticastSocket socket = new MulticastSocket(6969);
        InetAddress address = InetAddress.getByName("224.7.7.7");
        socket.joinGroup(address);
        
        //Receive file Data from server
        byte[] data = new byte[1024];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        while(true){
            socket.receive(packet);
            String message = new String(packet.getData(), 0 ,packet.getLength());
            System.out.println("SERVER> " + message);
        }
    }
}
