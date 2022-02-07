/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThreadChat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class handlerClients implements Runnable{
    public static ArrayList<SocketChannel> clientList;
    handlerClients(){
        this.clientList = new ArrayList<>();
    }
    
    @Override
    public void run() {
        InetSocketAddress address = new InetSocketAddress(6969);
        System.out.println("Opening port...\n");
        try(ServerSocketChannel server = ServerSocketChannel.open()){
            server.bind(address);
            while(server.isOpen()){
                SocketChannel channel = server.accept();
                System.out.println("Received connection from " + channel.getRemoteAddress());
                
                //Adding accepted channel to clientList
                clientList.add(channel);
                
                //Receive Thread for each client channel
                Thread  receiverThread = new Thread(){
                  public void run(){
                      String message;
                      try {
                        do{
                            message = ControllerMethod.receiveMessage(channel);
                            System.out.println(channel.getRemoteAddress() + ": " + message);
                            Thread.yield();
                        }while(!message.equals("q"));
                        ControllerMethod.sendMessage(channel, "q");
                        channel.close();
                    } catch (IOException ioEx) {
                        System.err.println("Error I/O: " +ioEx);
                    }
                  }  
                };
                receiverThread.start();
            }
        }catch(IOException ioEx){
            System.err.println("I/O error: " + ioEx.getMessage());
        }
    }
    public void broadcast(String message) throws IOException{
        removeDisconnectedClient();
        if(!clientList.isEmpty()){
            for(SocketChannel clientChannel : clientList){
                ControllerMethod.sendMessage(clientChannel, message);
            }
        }
        else System.err.println("No clients to message!!!");
    }
    public void removeDisconnectedClient(){
        for(int i = clientList.size() - 1; i >= 0; i--){
            if(!clientList.get(i).isOpen())
                clientList.remove(i);
        }
    }
}
