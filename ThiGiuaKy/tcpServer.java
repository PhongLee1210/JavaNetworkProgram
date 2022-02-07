/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThiGiuaKy;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


/**
 *
 * @author Dell
 */
public class tcpServer {
    public static void main(String[] args) throws IOException{
        De2();
    }
    public static void De1() throws IOException{
        //De 1
        String path = "E:\\LeThanhPhong-60136534\\JavaNetworkProgram\\Project\\NetworkProgramCourse\\src\\ThiGiuaKy\\serverfile.txt";
        RandomAccessFile file = new RandomAccessFile(path, "rw");
        FileChannel fileChannel = file.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(32);
        
        InetSocketAddress address = new InetSocketAddress(6969);
        System.out.println("Opening port...\n");
        ServerSocketChannel server = ServerSocketChannel.open();
        server.bind(address);
        SocketChannel channel = server.accept();
        System.out.println("Received connection from " + channel.getRemoteAddress());
        
        String message = "",response = "yes";
        if(!response.equals("yes"))
            ControllerMethod.sendMessage(channel, "no"); 
        else{
            ControllerMethod.sendMessage(channel, "yes");
            message = ControllerMethod.receiveMessage(channel);
            System.out.println("Received message: " +message);
            if(message.equals("file transfer")){
                ControllerMethod.sendMessage(channel, "File is transfering...");
                String fileData = "";
                while(fileChannel.read(buffer) != -1){
                    buffer.flip();
                    while(buffer.hasRemaining())
                        fileData += (char)buffer.get();
                    buffer.clear();
                }
                ControllerMethod.sendMessage(channel,fileData);
            }
        }
    }
    public static void De2() throws IOException{
        InetSocketAddress address = new InetSocketAddress(6969);
        System.out.println("Opening port...\n");
        ServerSocketChannel server = ServerSocketChannel.open();
        server.bind(address);
        SocketChannel channel = server.accept();
        System.out.println("Received connection from " + channel.getRemoteAddress());
        int countLogin = 0;
        while(countLogin < 3){
            ControllerMethod.sendMessage(channel, "Username && Password");
            String message = ControllerMethod.receiveMessage(channel);
            System.out.println("Received message: " + message);
            String userName = message.split(" ")[0];
            String passWord = message.split(" ")[1];

            if(userName.equals("Phong") && passWord.equals("123")){
                ControllerMethod.sendMessage(channel, "Gia vang");
                break;
            }
            countLogin = countLogin + 1;
        }   ControllerMethod.sendMessage(channel, "bye");
    }  
}
