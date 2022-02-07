/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThiThu.Bai1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 *
 * @author Dell
 */
public class UDPClient {
    public static void main(String[] args) throws IOException {
        System.out.println("UDP Client started!!!");
        InetSocketAddress serverAddress = new InetSocketAddress("localhost", 6969);
        DatagramSocket clientSocket = new DatagramSocket();
        String path = "E:\\LeThanhPhong-60136534\\JavaNetworkProgram\\Project\\NetworkProgramCourse\\src\\ThiThu\\Bai1\\clientData.txt";
        FileChannel fChannel = FileChannel.open(Paths.get(path), StandardOpenOption.WRITE);
        DatagramPacket packet;
        
        //Send request to server
        String message = "File Transfer!!!";
        packet = new DatagramPacket(message.getBytes(), message.length(), serverAddress);
        clientSocket.send(packet);
        
        //Received file server data and save to clientData.txt
        byte[] fileData = new byte[2048];
        packet = new DatagramPacket(fileData, fileData.length);
        clientSocket.receive(packet);
        //Save to clientData.txt
        ByteBuffer buffer = ByteBuffer.wrap(fileData);
        fChannel.write(buffer);
        fChannel.close();
        
    }
}
