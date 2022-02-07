/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThiCuoiKy.Cau2_1;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 *
 * @author Dell
 */
public class Bai2_ServerTCP {
    public static void main(String[] args) throws IOException {
        System.out.println("TCP Server started!!!");
        InetSocketAddress address = new InetSocketAddress(5000);
        ServerSocketChannel server = ServerSocketChannel.open();
        server.bind(address);
        SocketChannel channel = server.accept();
        System.out.println("Received connection from " + channel.getRemoteAddress());
        //Send message to client
        ControllerMethod.sendMessage(channel, "Hello Client!!!");
        String message = "";
        message = ControllerMethod.receiveMessage(channel);
        System.out.println("CLIENT> " + message);
        //Bai 2.1
        String userName = "";
        while(!userName.equals("PhongLe")){
            //Ask for userName
            ControllerMethod.sendMessage(channel, "UserName!!!");
            userName = ControllerMethod.receiveMessage(channel);
        }
        String passWord = "";
        while(!passWord.equals("123")){
            //Ask for userName
            ControllerMethod.sendMessage(channel, "PassWord!!!");
            passWord = ControllerMethod.receiveMessage(channel);
        }
        ControllerMethod.sendMessage(channel, "Connect Success!!!");

        //Bai 2.2
        String clientInfo = "";
        do{
            clientInfo = ControllerMethod.receiveMessage(channel);
            System.out.println("CLIENT> " + clientInfo);
            InetAddress DNSaddress = InetAddress.getByName(clientInfo);
            ControllerMethod.sendMessage(channel, DNSaddress.toString());
        }while(!clientInfo.equals("bye"));
        System.out.println("Done.");
    }
}
