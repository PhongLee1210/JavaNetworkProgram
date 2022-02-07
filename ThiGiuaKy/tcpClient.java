/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThiGiuaKy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class tcpClient {
    public static void main(String[] args) throws IOException
    {
        De2();
    }
    public static void De1() throws IOException{
        InetSocketAddress address = new InetSocketAddress("localhost", 6969);
        SocketChannel channel = SocketChannel.open(address);
        String message = "file transfer",response;
        try{
            response = ControllerMethod.receiveMessage(channel);
            System.out.println("SERVER> "+ response);
            
            if(response.equals("no"))   channel.close();
            else if(response.equals("yes"))
            {
                ControllerMethod.sendMessage(channel, message);
                response = ControllerMethod.receiveMessage(channel);
                System.out.println("SERVER> "+ response);
                
                String path = "E:\\LeThanhPhong-60136534\\JavaNetworkProgram\\Project\\NetworkProgramCourse\\src\\ThiGiuaKy\\clientfile.txt";
                String serverFileData = ControllerMethod.receiveMessage(channel);
                FileChannel fileChannel = FileChannel.open(Paths.get(path),StandardOpenOption.APPEND);
                ByteBuffer buffer = ByteBuffer.wrap(serverFileData.getBytes());
                fileChannel.write(buffer);
            }
        } catch (IOException ioEx) {
            System.err.println("I/O error: " + ioEx.getMessage());
        }
    }
    public static void De2() throws IOException{
        InetSocketAddress address = new InetSocketAddress("localhost", 6969);
        SocketChannel channel = SocketChannel.open(address);
        Scanner input = new Scanner(System.in);
        String message,response;
        try{
            while(true)
            {
                response = ControllerMethod.receiveMessage(channel);
                System.out.println("SERVER> "+ response);
                if(response.equals("Gia vang"))
                {
                    System.out.println("Login Success!!!");
                    break;  
                }else if (response.equals("bye"))
                    break;
                System.out.print("Send: "); message = input.nextLine();
                ControllerMethod.sendMessage(channel, message);
            }
            channel.close();
        } catch (IOException ioEx) {
            System.err.println("I/O error: " + ioEx.getMessage());
        }
    }
}
