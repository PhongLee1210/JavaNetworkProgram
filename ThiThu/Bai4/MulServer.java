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
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;

/**
 *
 * @author Dell
 */
public class MulServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Multicast Server started!!!");
        MulticastSocket socket = new MulticastSocket();
        InetAddress address = InetAddress.getByName("224.7.7.7");
        socket.joinGroup(address);
        String path = "E:\\LeThanhPhong-60136534\\JavaNetworkProgram\\Project\\NetworkProgramCourse\\src\\ThiThu\\Bai4\\serverData.txt";
        FileChannel fChannel = FileChannel.open(Paths.get(path));
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        
        //Read file data   
        byte[] data = new byte[1024];
        DatagramPacket packet;
        String fileData = "";
        while(fChannel.read(buffer) != -1){
            buffer.flip();
            while(buffer.hasRemaining())
                fileData += (char) buffer.get();
            buffer.clear();
        }
        //Send each Line of data
        for(int i = 0; i < fileData.split("\n").length; i++){
            data = fileData.split("\n")[i].getBytes();
            System.out.println("Send: " + fileData.split("\n")[i]);
            packet = new DatagramPacket(data, data.length, address, 6969);
            socket.send(packet);
            Thread.sleep(5000);
        }
        fChannel.close();
    }
}
