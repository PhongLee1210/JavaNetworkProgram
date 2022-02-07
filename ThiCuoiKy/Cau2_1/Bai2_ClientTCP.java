/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThiCuoiKy.Cau2_1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class Bai2_ClientTCP {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("TCP Server started!!!");
        InetSocketAddress serverAddress = new InetSocketAddress("localhost", 5000);
        SocketChannel channel = SocketChannel.open(serverAddress);
        String message = "";
        message = ControllerMethod.receiveMessage(channel);
        System.out.println("SERVER> " + message);
        //Send message to server
        ControllerMethod.sendMessage(channel, "Hello Server!!!");
        
        message = ControllerMethod.receiveMessage(channel);
        System.out.println("SERVER> " + message);
        Scanner input = new Scanner(System.in);
        //Send username to server
        while(true){
            System.out.print("Send UserName: "); message = input.nextLine();
            ControllerMethod.sendMessage(channel, message);
            message = ControllerMethod.receiveMessage(channel);
            System.out.println("SERVER> " + message);
            if (!message.equals("UserName!!!")) break;
        }
        //Send password to server
        while(true){
            //Send password to server
            System.out.print("Send PassWord: "); message = input.nextLine();
            ControllerMethod.sendMessage(channel, message);
            message = ControllerMethod.receiveMessage(channel);
            System.out.println("SERVER> " + message);
            if (!message.equals("PassWord!!!")) break;
        }
        //Connect Success!!!
        message = ControllerMethod.receiveMessage(channel);
        System.out.println("SERVER> " + message);
        //Bai 2.2
        String url = "";
        do{
            System.out.print("Send URL: "); url = input.nextLine();
            ControllerMethod.sendMessage(channel, url);
            message = ControllerMethod.receiveMessage(channel);
            System.out.println("SERVER> " + message);
        }while(!url.equals("bye"));
        channel.close();
    }
}
