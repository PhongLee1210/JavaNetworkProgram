/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThiCuoiKy;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 *
 * @author Dell
 */
public class Bai1_ServerUDP {
    public static void main(String[] args) throws IOException {
        System.out.println("UDP Server Started!!!");
        DatagramSocket serverSocket = new DatagramSocket(6969);
        String path = "E:\\LeThanhPhong-60136534\\JavaNetworkProgram\\Project\\NetworkProgramCourse\\src\\ThiCuoiKy\\serverFile.txt";
        FileChannel fChannel = FileChannel.open(Paths.get(path), 
                            StandardOpenOption.WRITE);
        byte[] data = new byte[1024];
        DatagramPacket packet;
        
        //Receive data from client
        packet = new DatagramPacket(data, data.length);
        serverSocket.receive(packet);
        String fileData = new String(packet.getData(), 0, packet.getLength());
        
        //put data to file
        ByteBuffer buffer = ByteBuffer.wrap(fileData.getBytes());
        fChannel.write(buffer);
        fChannel.close();
        
    }
}
