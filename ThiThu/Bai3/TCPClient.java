/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThiThu.Bai3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 *
 * @author Dell
 */
public class TCPClient {
    public static void main(String[] args) throws IOException {
        InetSocketAddress serverAddress = new InetSocketAddress("localhost", 6969);
        SocketChannel channel = SocketChannel.open(serverAddress);
        
        //Send dowload request
        ControllerMethod.sendMessage(channel, "Dowload!!!");
        //Receive File
        ReceiveFile(channel);
        channel.close();
        
    }
    public static void ReceiveFile(SocketChannel channel) throws IOException{
        String path = "E:\\LeThanhPhong-60136534\\JavaNetworkProgram\\Project\\NetworkProgramCourse\\src\\ThiThu\\Bai3\\clientData.txt";
        FileChannel fChannel = FileChannel.open(Paths.get(path), StandardOpenOption.APPEND);
        String fileData = ControllerMethod.receiveMessage(channel);
        ByteBuffer buffer = ByteBuffer.wrap(fileData.getBytes());
        fChannel.write(buffer);
        fChannel.close();
    }
}
