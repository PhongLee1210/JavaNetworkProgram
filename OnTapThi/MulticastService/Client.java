/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnTapThi.MulticastService;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Scanner;


/**
 *
 * @author Dell
 */
public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("Service Client Started!!!");
        InetSocketAddress serverAddress = new InetSocketAddress("localhost", 6969);
        SocketChannel channel = SocketChannel.open(serverAddress);
        
        Scanner input = new Scanner(System.in);
        String message = "";
        do{
            System.out.print("Send: "); message = input.nextLine();
            ControllerMethod.sendMessage(channel, message);
        }while(!message.equals("q"));
        channel.close();
        System.out.println("Done.");
    }
}
