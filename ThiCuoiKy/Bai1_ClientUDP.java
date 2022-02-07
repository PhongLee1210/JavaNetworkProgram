/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThiCuoiKy;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 *
 * @author Dell
 */
public class Bai1_ClientUDP {
    public static void main(String[] args) throws IOException {
        System.out.println("UDP Client Started!!!");
        InetSocketAddress serverAddress = new InetSocketAddress("localhost", 6969);
        DatagramSocket clientSocket = new DatagramSocket();
        
        String clientData = "Hom nay thi lap trinh mang.";
        byte[] data = new byte[1024];
        data = clientData.getBytes();
        //Receive data from client
        DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress);
        clientSocket.send(packet);
    }
}
