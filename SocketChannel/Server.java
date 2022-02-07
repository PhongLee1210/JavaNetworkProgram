/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketChannel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.text.*;
import java.util.Date;


/**
 *
 * @author Dell
 */
public class Server {
    public static void main(String[] args) throws FileNotFoundException {
        String userDirpath = System.getProperty("user.dir");
        String loggerpath = userDirpath + "\\src\\SocketChannel\\ServerLogs\\logger.txt";
        RandomAccessFile logger = new RandomAccessFile(loggerpath, "rw");
       
        InetSocketAddress address = new InetSocketAddress(6969);
        try(ServerSocketChannel server = ServerSocketChannel.open()){
            server.bind(address);
            System.out.println("Waiting...");
            SocketChannel channel = server.accept();
            String a = "",b = "";String rep = "";
            
            DateFormat Dateformat = new SimpleDateFormat("(yy-MM-dd hh:mm:ss)");
            String date = Dateformat.format(new Date());
            
            String timeInfoServer = date.toString() + channel.getLocalAddress() + ": ";
            String timeInfoClient = date.toString() + channel.getRemoteAddress()+ ": ";
            
            if (channel != null){
                System.out.println("Received connection from " + channel.getRemoteAddress());
                
//                rep = "Chào Client";
//                HelperMethod.sendMessage2(channel, rep);
//                logger.write((timeInfoServer + rep).getBytes());  logger.writeBytes("\n");
                
                a = HelperMethod.receiveMessage2(channel);
                Float.parseFloat(a.toString());
                System.out.println("Receive from " + channel.getRemoteAddress() + ": " + a);
                //logger.write((timeInfoClient + a).getBytes());  logger.writeBytes("\n");
                
                b = HelperMethod.receiveMessage2(channel);
                Double.parseDouble(b.toString());
                System.out.println("Receive from " + channel.getRemoteAddress() + ": " + b);
                //logger.write((timeInfoClient + b).getBytes());  logger.writeBytes("\n"); 
                
                HelperMethod.sendMessage2(channel,String.valueOf(a+b));
                logger.write((timeInfoServer + rep).getBytes());  logger.writeBytes("\n");
            }
            channel.close();
        }catch(IOException e){
            System.err.println("I/O error: " + e.getMessage());
        }finally{
            System.out.println("Done.");
        }
    }
}
