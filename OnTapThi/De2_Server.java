/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnTapThi;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Dell
 */
public class De2_Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        InetSocketAddress address = new InetSocketAddress(6969);
        System.out.println("Server started!!!");
        ServerSocketChannel server = ServerSocketChannel.open();
        server.bind(address);
        do{
            SocketChannel channel = server.accept();
            System.out.println("Received connection from " + channel.getRemoteAddress());
            //Connection success
            ControllerMethod.sendMessage(channel, "Connect success!!!");
            Thread.sleep(200);
            
            int countLogin = 0;
            while(countLogin < 3){
                ControllerMethod.sendMessage(channel, "Username && Password!!!");
                String message = "";
                message = ControllerMethod.receiveMessage(channel);
                System.out.println("Received message: " + message);
                String userName = message.split(" ")[0];
                String passWord = message.split(" ")[1];
                
                if(userName.equals("Phong") && passWord.equals("123")){
                    ControllerMethod.sendMessage(channel, "Login Success!!!");
                    Thread.sleep(200);
                    ControllerMethod.sendMessage(channel, "1 USD/Kg");
                    break;
                }
                countLogin += 1;
            }
            ControllerMethod.sendMessage(channel, "bye!!!");
        }while(true);
    }
}
