/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculator;

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
        private static void chatServer(SocketChannel channel){
            String a,b,calculation,response;
            Scanner input = new Scanner(System.in);
            try{

                    System.out.print("Enter a: ");  a = input.nextLine();  
                    ControllerMethod.sendMessage(channel, a);
                    
                    System.out.print("Enter b: ");  b = input.nextLine();  
                    ControllerMethod.sendMessage(channel, b);
                    
                    System.out.print("Enter calculation: ");  calculation = input.nextLine();  
                    ControllerMethod.sendMessage(channel, calculation);
                    
                    System.out.println("Waitting for message from server ...");
                    response = ControllerMethod.receiveMessage(channel);
                    System.out.println("SERVER> " + response);
                    channel.close();

            }catch(IOException ioEx){
                System.err.println("I/O error: " + ioEx.getMessage());
            }
        }
}
