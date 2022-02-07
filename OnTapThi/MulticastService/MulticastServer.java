/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnTapThi.MulticastService;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Dell
 */
public class MulticastServer {
    private static Selector selector;
    private static MulticastSocket multicastSocket;
    private static InetAddress address;
    
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
                                //Read data from channel
                                SocketChannel channel = (SocketChannel) key.channel();
                                String message = ControllerMethod.receiveMessage(channel);
                                System.out.println(channel + ": " + message);
                                if(message.equals("q")) channel.close();
                                //multiCast all message to all clients
                                else    multiCast(message);
                            }
                            keyIterator.remove();
                        }
                    }
                }catch(IOException IOEx){
                    IOEx.printStackTrace();
                }
            }
        }
        public static void multiCast(String message) throws IOException{
            byte[] data = message.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, address, 6969);
            multicastSocket.send(packet);
        }
    }
    public static void main(String[] args) {
        System.out.println("Multicast Server Started!!!");
        try{
            multicastSocket = new MulticastSocket();
            address = InetAddress.getByName("224.7.7.7");
            multicastSocket.joinGroup(address);
            
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
