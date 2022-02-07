/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThreadChat;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class SenderThread implements Runnable{
    private SocketChannel channel;
    private volatile boolean stopped = false;
    
    SenderThread(SocketChannel channel) throws IOException{
        this.channel = channel;
    }
    public void halt(){this.stopped = true;}
    @Override
    public void run(){
        Scanner input = new Scanner(System.in);
        String message = "";
        try{
            do{
                if(stopped) return;
                message = input.nextLine();
                ControllerMethod.sendMessage(channel, message);
                Thread.yield();
            }while(!message.equals("q"));
            channel.close();
        }catch(IOException ioEx){
            System.err.println("I/O error: " + ioEx.getMessage());
        }
    }
}
