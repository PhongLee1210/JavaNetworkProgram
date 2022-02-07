/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Selector;

import ThreadChat.*;
import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class SenderThread implements Runnable{
    private SocketChannel channel;
    
    SenderThread(SocketChannel channel){
        this.channel = channel;
    }
    
    @Override
    public void run(){
        Scanner input = new Scanner(System.in);
        String message = "";
        try{
            do{
                message = input.nextLine();
                ControllerMethod.sendMessage(channel, message);
                if(message.equals("q")) channel.close();
                Thread.yield();
            }while(channel.isConnected());
        }catch(IOException IOEx){
            
        }
        
    }
}
