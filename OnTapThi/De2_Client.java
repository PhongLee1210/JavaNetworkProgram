/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnTapThi;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Scanner;


/**
 *
 * @author Dell
 */
public class De2_Client {
    public static void main(String[] args) throws IOException {
        InetSocketAddress address = new InetSocketAddress("localhost", 6969);
        SocketChannel channel = SocketChannel.open(address);
        
        String message = "";
        message = ControllerMethod.receiveMessage(channel);
        System.out.println("SERVER> " + message);
        
        Scanner input = new Scanner(System.in);
        while(true){
            message = ControllerMethod.receiveMessage(channel);
            System.out.println("SERVER> " + message);
            
            if(message.equals("Login Success!!!")){
                message = ControllerMethod.receiveMessage(channel);
                System.out.println("SERVER> " + message);
                ControllerMethod.sendMessage(channel, "bye"); 
                break;
            }else if (message.equals("bye!!!")) break;
            System.out.print("Send: "); message = input.nextLine();
            ControllerMethod.sendMessage(channel, message);
        }
        channel.close();
    }
}
