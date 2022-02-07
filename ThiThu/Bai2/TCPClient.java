/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThiThu.Bai2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class TCPClient {
    public static void main(String[] args) throws IOException {
        System.out.println("TCP Client started!!!");
        InetSocketAddress serverAddress = new InetSocketAddress("localhost", 6969);
        SocketChannel channel = SocketChannel.open(serverAddress);
        //Send array
        Scanner input = new Scanner(System.in);
        String message = "";
        System.out.print("Send: "); message = input.nextLine();
        ControllerMethod.sendMessage(channel, message);
        //Receive array from server
        message = ControllerMethod.receiveMessage(channel);
        System.out.println("SERVER> " + message);
        
        
        
    }
}
