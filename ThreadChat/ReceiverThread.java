/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThreadChat;

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
    private volatile boolean stopped = false;
    public ReceiverThread(SocketChannel channel){
        this.channel = channel;
    }
    public void halt(){this.stopped = true;}
    
    @Override
    public void run(){
        try{
            String message = "";
            do{
                if(stopped) return;
                message = ControllerMethod.receiveMessage(channel);
                System.out.println(channel.getRemoteAddress() +": "+ message);
                Thread.yield();
            }while(!message.equals("q"));
            channel.close();
        }catch(IOException ioEx){
            System.err.println(ioEx);
        }
    }
}
