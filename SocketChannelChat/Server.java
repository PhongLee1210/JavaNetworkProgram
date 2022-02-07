/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketChannelChat;

import SocketChannelChat.ControllerMethod;
import java.io.*;
import java.nio.channels.*;
import java.net.InetSocketAddress;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class Server {
        public static void main(String[] args){
            InetSocketAddress address = new InetSocketAddress(6969);
            System.out.println("Opening port...\n");
            try(ServerSocketChannel server = ServerSocketChannel.open()){
                server.bind(address);
                chatClient(server);
                
            }catch(IOException ioEx){
                System.err.println("I/O error: " + ioEx.getMessage());
            }finally{
                System.out.println("Done.");
            }
    }
        private static void chatClient(ServerSocketChannel server) throws FileNotFoundException{
            String userDirpath = System.getProperty("user.dir");
            String loggerpath = userDirpath + "\\src\\SocketChannelChat\\ServerLogs\\logger.txt";
            RandomAccessFile file = new RandomAccessFile(loggerpath ,"rw");
            
            String message = "", response = "";
            Scanner input = new Scanner(System.in);
            try(SocketChannel channel = server.accept()){
                System.out.println("Received connection from " + channel.getRemoteAddress());
                file.seek(file.length());
                file.write(("==========" + channel.getRemoteAddress() + "==========\n").getBytes());
                do {
                    System.out.print("Enter message: "); message = input.nextLine();
                    if (!message.equals("q")) {
                        ControllerMethod.sendMessage(channel, message);
                        ControllerMethod.writeLog(loggerpath, channel.getLocalAddress().toString(), message);

                        System.out.println("Waitting message from client...");
                        response = ControllerMethod.receiveMessage(channel);
                        ControllerMethod.writeLog(loggerpath, channel.getRemoteAddress().toString() ,response);
                        System.out.println("CLIENT> " + response);
                    }else{
                        ControllerMethod.sendMessage(channel, "Disconnect!");
                        channel.close();
                        server.close();
                        break;
                    }
                }while(true);
            }catch(IOException ioEx){
                System.err.println("I/O error: " + ioEx.getMessage());
            }
        }
}
