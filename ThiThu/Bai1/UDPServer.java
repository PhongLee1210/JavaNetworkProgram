/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThiThu.Bai1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;

/**
 *
 * @author Dell
 */
public class UDPServer {
    public static void main(String[] args) throws IOException {
        System.out.println("UDP Server started!!!");
        DatagramSocket serverSocket = new DatagramSocket(6969);;
        String path = "E:\\LeThanhPhong-60136534\\JavaNetworkProgram\\Project\\NetworkProgramCourse\\src\\ThiThu\\Bai1\\serverData.txt";
        FileChannel fChannel = FileChannel.open(Paths.get(path));
        ByteBuffer buffer = ByteBuffer.allocate(2048);
        
        byte[] data = new byte[1024];
        DatagramPacket packet;
        //Receiving request from client
        packet = new DatagramPacket(data, data.length);
        serverSocket.receive(packet);
        String message = new String(packet.getData(), 0, packet.getLength());
        System.out.println("CLIENT> " + message);
        
        //Send file data to client
        String fileData = "";
        while(fChannel.read(buffer) != -1){
            buffer.flip();
            while(buffer.hasRemaining())
                fileData += (char) buffer.get();
            buffer.clear();
        }
        data = fileData.getBytes();
        packet = new DatagramPacket(data, data.length, packet.getSocketAddress());
        serverSocket.send(packet);
        
    }
}
