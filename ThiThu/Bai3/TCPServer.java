/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThiThu.Bai3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Dell
 */
public class TCPServer {
    public static void main(String[] args) throws IOException {
        //Started server
        Selector selector = Selector.open();
        InetSocketAddress address = new InetSocketAddress(6969);
        ServerSocketChannel server = ServerSocketChannel.open();
        server.configureBlocking(false);
        server.bind(address);
        
        //ServerSocketChannel register key in selector
        server.register(selector, SelectionKey.OP_ACCEPT);
        
        do{
            selector.select(500);
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = keys.iterator();
            while(keyIterator.hasNext()){
                SelectionKey key = keyIterator.next();
                if(key.isAcceptable()){
                    SocketChannel channel = ((ServerSocketChannel) key.channel()).accept();
                    //Connection success
                    System.out.println("Received connection from " + channel.getRemoteAddress());
                    channel.configureBlocking(false);
                    channel.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ);
                }
                else if(key.isReadable()){
                    SocketChannel channel = (SocketChannel) key.channel();
                    String message = ControllerMethod.receiveMessage(channel);
                    System.out.println(channel.getRemoteAddress() + ": " + message);
                    sendFile(channel);
                    channel.close();
                }
                keyIterator.remove();
            }
        }while(true);
    }
    public static void sendFile(SocketChannel channel) throws IOException{
        String path = "E:\\LeThanhPhong-60136534\\JavaNetworkProgram\\Project\\NetworkProgramCourse\\src\\ThiThu\\Bai3\\serverData.txt";
        FileChannel fChannel = FileChannel.open(Paths.get(path));
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while(fChannel.read(buffer) != -1){
            buffer.flip();
            channel.write(buffer);
            buffer.clear();
        }
        fChannel.close();
    }
}
