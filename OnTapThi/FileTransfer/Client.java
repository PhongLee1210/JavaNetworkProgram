/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnTapThi.FileTransfer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

/**
 *
 * @author Dell
 */
public class Client {
    public static void main(String[] args) throws IOException {
        InetSocketAddress serverAddress = new InetSocketAddress("localhost", 6969);
        SocketChannel channel = SocketChannel.open(serverAddress);
        
        //Received file
        ReceiveFile(channel);
        channel.close();
        System.out.println("Done.");
    }
    public static void ReceiveFile(SocketChannel channel) throws IOException{
        String path = "E:\\LeThanhPhong-60136534\\JavaNetworkProgram\\Project\\NetworkProgramCourse\\src\\OnTapThi\\FileTransfer\\clientfile.txt";
        FileChannel fChannel = FileChannel.open(Paths.get(path), StandardOpenOption.WRITE);
        String fData = ControllerMethod.receiveMessage(channel);
        ByteBuffer buffer = ByteBuffer.wrap(fData.getBytes());
        fChannel.write(buffer);
        fChannel.close();
    }
}
