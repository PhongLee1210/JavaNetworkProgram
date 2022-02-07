/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnTapThi;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 *
 * @author Dell
 */
public class De1_Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        String path = "E:\\LeThanhPhong-60136534\\JavaNetworkProgram\\Project\\NetworkProgramCourse\\src\\OnTapThi\\serverfile.txt";
        RandomAccessFile file = new RandomAccessFile(path, "rw");
        FileChannel fChannel = file.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(256);
        
        //Open Server
        InetSocketAddress address = new InetSocketAddress(6969);
        System.out.println("Server started!!!");
        ServerSocketChannel server = ServerSocketChannel.open();
        server.bind(address);
        
        SocketChannel channel = server.accept();
        System.out.println("Received connection from " + channel.getRemoteAddress());
        //Connection success
        ControllerMethod.sendMessage(channel, "Connect success!!!");
        
        //Listen request
        String message = ControllerMethod.receiveMessage(channel);
        System.out.println(message);
        
        String condition = "yes";
        if(condition.equals("no"))
            ControllerMethod.sendMessage(channel, "no!!!");
        else{
            ControllerMethod.sendMessage(channel, "File is transfering...");
            String fileData = "";
            System.out.println("File is transfering");
            while(fChannel.read(buffer) != -1){
                buffer.flip();
                
                while(buffer.hasRemaining())
                    fileData += (char) buffer.get();
                
                buffer.clear();
            }
            ControllerMethod.sendMessage(channel, fileData);
            System.out.println("Transfering DONE!!!");
            Thread.sleep(500);
            ControllerMethod.sendMessage(channel, "Terminate");
        }
        System.out.println("Done");
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void Bai2_1() throws IOException{
        InetSocketAddress address = new InetSocketAddress(6969);
        System.out.println("Server started!!!");
        ServerSocketChannel server = ServerSocketChannel.open();
        server.bind(address);
        
        SocketChannel channel = server.accept();
        System.out.println("Received connection from " + channel.getRemoteAddress());
        
        //Connection success
        ControllerMethod.sendMessage(channel, "Connect success!!!");
        
        //Received request
        String message = "";
        message = ControllerMethod.receiveMessage(channel);
        if(!message.equals("Gold Price"))
            ControllerMethod.sendMessage(channel, "Wrong Syntax!!!");
        else{
            ControllerMethod.sendMessage(channel, "1 USD/Kg");
        }
        System.out.println("Done");
    }
}
