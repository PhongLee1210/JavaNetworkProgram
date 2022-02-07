/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketChannel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Dell
 */
public class HelperMethod {
    public static void sendMessage1(SocketChannel channel, String message) throws IOException{
        ByteBuffer buffer = ByteBuffer.allocate(256);
        buffer.put(message.getBytes());
        buffer.flip();
        channel.write(buffer);
        System.out.println("Send: " + message);
    }
    public static String receiveMessage1(SocketChannel channel) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(256);
        String message = "";
        buffer.clear();
        while(channel.read(buffer) != -1){
            buffer.flip();
            while(buffer.hasRemaining()){
                message += (char)buffer.get();
            }
            if(buffer.limit() < buffer.capacity()) break;
            else buffer.clear();
        }
        return message;
    }
    public static void sendMessage2(SocketChannel channel, String message) throws IOException{
        ByteBuffer buffer = ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8));
        channel.write(buffer);
        System.out.println("Send: " + message);
    }
    public static String receiveMessage2(SocketChannel channel) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer);
        buffer.flip();
        int limit = buffer.limit();
        String message = new String(buffer.array(), 0, limit, StandardCharsets.UTF_8);
        
        return message;
    }
}
