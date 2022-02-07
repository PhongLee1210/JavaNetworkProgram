/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThiThu.Bai3;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;


/**
 *
 * @author Dell
 */
public class ControllerMethod {
    public static void sendMessage(SocketChannel channel, String message) throws IOException{
        ByteBuffer buffer = ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8));
        channel.write(buffer);
    }
    public static String receiveMessage(SocketChannel channel) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer);
        buffer.flip();
        int limit = buffer.limit();
        String message = new String(buffer.array(), 0, limit, StandardCharsets.UTF_8);
        
        return message;
    }
}
