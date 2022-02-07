/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThreadChat;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class chatServer implements Runnable{
    private SocketChannel channel;
    
    chatServer(SocketChannel channel){
        this.channel = channel;
    }
    
    @Override
    public void run() {
        String message = "";
        Scanner input = new Scanner(System.in);
        try {
            do{
                message = input.nextLine();
                ControllerMethod.sendMessage(channel, message);
                Thread.yield();
            }while(!message.equals("q"));
        } catch (IOException ioEx) {
                System.err.println("Error I/O: " + ioEx);
        }
    }
    
}
