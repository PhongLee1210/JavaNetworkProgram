/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnTapThi;

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
public class De1_Client {
    public static void main(String[] args) throws IOException {
        InetSocketAddress address = new InetSocketAddress("localhost", 6969);
        SocketChannel channel = SocketChannel.open(address);
        
        String message = "";
        message = ControllerMethod.receiveMessage(channel); System.out.println(message);
        
        ControllerMethod.sendMessage(channel, "File Transfer!!!");
        message = ControllerMethod.receiveMessage(channel); System.out.println(message);
        if(message.equals("no!!!")) channel.close();
        else{
            String serverFileData = ControllerMethod.receiveMessage(channel);
            String path = "E:\\LeThanhPhong-60136534\\JavaNetworkProgram\\Project\\NetworkProgramCourse\\src\\OnTapThi\\clientfile.txt";
            FileChannel fChannel = FileChannel.open(Paths.get(path), StandardOpenOption.APPEND);
            ByteBuffer buffer = ByteBuffer.wrap(serverFileData.getBytes());
            fChannel.write(buffer);
            fChannel.close();
            
            String stopConnect = ControllerMethod.receiveMessage(channel);  System.out.println(stopConnect);
            if(stopConnect.equals("Terminate")) channel.close();
            
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static void Bai2_1() throws IOException{
        InetSocketAddress address = new InetSocketAddress("localhost", 6969);
        SocketChannel channel = SocketChannel.open(address);
        
        String message = "";
        message = ControllerMethod.receiveMessage(channel); System.out.println(message);
        
        //Send request
        ControllerMethod.sendMessage(channel, "Gold Price");
        message = ControllerMethod.receiveMessage(channel); System.out.println(message);
        channel.close();
        System.out.println("Done");
    }
}
