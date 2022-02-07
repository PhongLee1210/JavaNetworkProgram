/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketChannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;

/**
 *
 * @author Dell
 */
public class Client {
    public static void main(String[] args) {
        InetSocketAddress address = new InetSocketAddress("localhost", 6969);
        try(SocketChannel client = SocketChannel.open(address)){
            String mess = "";
            if(client != null)
            {
                HelperMethod.sendMessage2(client,"2.0");
                HelperMethod.sendMessage2(client,"3.0");
                mess = HelperMethod.receiveMessage2(client);
                System.out.println("Receive from " + client.getRemoteAddress() + ": " + mess);
//            
//                mess = HelperMethod.receiveMessage2(client);
//                System.out.println("Receive from " + client.getRemoteAddress() + ": " + mess);
//                HelperMethod.sendMessage2(client,"Chán!!!");
            }
            
            client.close();
        }catch(IOException e){
            System.err.println("I/O error: " + e.getMessage());
        }finally{
            System.out.println("Done.");
        }
    }
}
