/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatagramChannel;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Dell
 */
public class EchoServer_Channel {
    public static void main(String[] args) {
        int port = 6969;
        System.out.println("UDP Echo Server Started");
        try{
            DatagramChannel channel = DatagramChannel.open();
            DatagramSocket socket = channel.socket();
            SocketAddress address = new InetSocketAddress(port);
            socket.bind(address);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while(true){
                System.out.println("Waiting...");
                SocketAddress client = channel.receive(buffer);
                
                buffer.flip();
                int limit = buffer.limit();
                String receiveMess = new String(buffer.array(), 0, limit, StandardCharsets.UTF_8);
                System.out.println("Received packet from " + client + ": [" + receiveMess + "]");
                channel.send(buffer, client);
                System.out.println("Reply: [" + receiveMess + "]");
                buffer.clear();
            }
        }
        catch(IOException IoEx)
        {
            System.err.println("I/O Error: " + IoEx);
        }
        System.out.println("Done.");
    }
}
