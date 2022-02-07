/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Selector;

import ThreadChat.*;
import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class ReceiverThread implements Runnable{
    private SocketChannel channel;
   
    public ReceiverThread(SocketChannel channel){
        this.channel = channel;
    }
    
    @Override
    public void run(){
        String message = "";
        try{
            do{
                message = ControllerMethod.receiveMessage(channel);
                System.out.println(channel.getRemoteAddress() + ": " + message);
                Thread.yield();
            }while(channel.isConnected());
        }catch(IOException IOEx){
            
        }
    }
}
