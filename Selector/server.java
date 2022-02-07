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
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Dell
 */
public class server {
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
                                
                                String message = ControllerMethod.receiveMessage(channel);
                                
                                double a = Double.parseDouble(message.split(" ")[0]);
                                String calculation = message.split(" ")[1];
                                double b = Double.parseDouble(message.split(" ")[2]);
                                
                                double c = 0;   String response = "";

                                switch(calculation) {
                                    case "+":   
                                        c = a + b;
                                        response = String.format("%.2f + %.2f = %.2f", a, b, c);
                                      break;

                                    case "-":   
                                        c = a - b;
                                        response = String.format("%.2f - %.2f = %.2f", a, b, c);
                                      break;

                                    case "*":   
                                        c = a * b;
                                        response = String.format("%.2f * %.2f = %.2f", a, b, c);
                                    break;

                                    case "/":   
                                        c = a / b;
                                        response = String.format("%.2f / %.2f = %.2f", a, b, c);
                                    break;

                                    default:
                                        ControllerMethod.sendMessage(channel, "Cannot determine the calculation!!!");
                                    break;
                                  }
                                System.out.println("Answer: " + response);
                                ControllerMethod.sendMessage(channel, response);
                                channel.close();
                                
                            }
                            keyIterator.remove();
                        }
                    }
                }catch(IOException IOEx){
                    IOEx.printStackTrace();
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
