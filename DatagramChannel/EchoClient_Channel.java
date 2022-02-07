/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatagramChannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class EchoClient_Channel {
    public static void main(String[] args) throws IOException {
        System.out.println("UDP Echo Client Started");
        SocketAddress remote = new InetSocketAddress("localhost", 6969);
        DatagramChannel channel = DatagramChannel.open();
        channel.connect(remote);
        Scanner input = new Scanner(System.in);
        String message = "";
        do{
            System.out.print("Send: "); message = input.nextLine();
            ByteBuffer buffer = ByteBuffer.allocate(message.length());
            buffer.put(message.getBytes(StandardCharsets.UTF_8));
            
            buffer.flip();
            channel.write(buffer);
            buffer.clear();

            channel.read(buffer);
            buffer.flip();
            int limit = buffer.limit();
            String receiveMess = new String(buffer.array(), 0, limit, StandardCharsets.UTF_8);
            System.out.println("Received: "+ "["+receiveMess + "]");
        }while(!message.equals("q"));
        channel.close();
        System.out.println("Done.");
    }
}
