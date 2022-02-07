/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThiGiuaKy;


import Thread.*;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    public static void writeLog(String path, String ip, String message) throws IOException{
        DateFormat Dateformat = new SimpleDateFormat("(dd-MM-yyyy hh:mm:ss)");
        StringBuilder builder = new StringBuilder();
        builder.append(Dateformat.format(new Date()));
        FileChannel fChannel = FileChannel.open(Paths.get(path), StandardOpenOption.APPEND);
        ByteBuffer buffer = ByteBuffer.wrap(((builder + ip + ": " + message + "\n").toString().getBytes(StandardCharsets.UTF_8)));
        fChannel.write(buffer);
        fChannel.close();      
    }
}
