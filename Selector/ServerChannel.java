/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;


/**
 *
 * @author Dell
 */
public class ServerChannel {
    private static Selector selector;
    static class SelectorHandler implements Runnable{
        @Override
        public void run() {
            while(true){
                try{
                    int readyChannel = selector.select(500);
                    if(readyChannel == 0) continue;
                    else{
                        Set<SelectionKey> keys = selector.selectedKeys();
                        Iterator<SelectionKey> keyIterator = keys.iterator();
                        while(keyIterator.hasNext()){
                            SelectionKey key = keyIterator.next();
                            if(key.isAcceptable()){
                                //Accept request
                                SocketChannel channel = ((ServerSocketChannel) key.channel()).accept();
                                System.out.println(channel + " channel is accepted");
                                channel.configureBlocking(false);
                                selector.wakeup();
                                channel.register(selector,
                                        SelectionKey.OP_READ|SelectionKey.OP_WRITE, null);
                            }
                            else if(key.isReadable()){
                                //Read && Write to channel
                                SocketChannel channel = (SocketChannel) key.channel();
                                //Read
                                String message = ControllerMethod.receiveMessage(channel);
                                System.out.println(channel + ": " + message);
                                if(message.equals("q")) channel.close();
                                
                                //Broadcast all message to all clients
                                broadCast(channel, message);
                                
                            }
                            keyIterator.remove();
                        }
                    }
                }catch(IOException IOEx){
                    IOEx.printStackTrace();
                }
            }
        }
        public static void broadCast(SocketChannel channel, String message) throws IOException{
            for(SelectionKey key : selector.keys()){
                if(key.isValid() && key.channel() instanceof SocketChannel){
                    if(!((SocketChannel) key.channel()).equals(channel))
                        ControllerMethod.sendMessage((SocketChannel) key.channel(), message);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Server Started!!!");
        try{
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.bind(new InetSocketAddress(6969));
            selector = Selector.open();
            
            serverChannel.configureBlocking(false);
            serverChannel.register(selector,
                    SelectionKey.OP_ACCEPT, null);
            
            new Thread(new SelectorHandler()).start();
        }catch(IOException IOEx){
            IOEx.printStackTrace();
        }
    }
}
