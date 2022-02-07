/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketChannelChat;

import SocketChannelChat.ControllerMethod;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class Client {
        public static void main(String[] args) {
            InetSocketAddress address = new InetSocketAddress("localhost", 6969);
            try(SocketChannel client = SocketChannel.open(address)){
                chatServer(client);
            }catch(IOException e){
                System.err.println("I/O error: " + e.getMessage());
            }finally{
                System.out.println("Done.");
            }
    }
        private static void chatServer(SocketChannel link){ 
            String message = "", response = "";
            Scanner input = new Scanner(System.in);
            try{
                do {
                    System.out.println("Waitting for message from server ...");
                    
                    response = ControllerMethod.receiveMessage(link);
                    System.out.println("SERVER> " + response);
                    
                    if (!response.equals("Disconnect!")){
                        System.out.print("Enter message: ");message = input.nextLine();
                        if (!message.equals("q")) 
                            ControllerMethod.sendMessage(link, message);
                        else{
                                ControllerMethod.sendMessage(link, "Client Disconnect!");
                                link.close();   break;
                            }
                    }else{
                        link.close();   break;
                    }
                }while(true);
            }catch(IOException ioEx){
                System.err.println("I/O error: " + ioEx.getMessage());
            }
        }
}
