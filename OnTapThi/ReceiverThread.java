/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnTapThi;

import Selector.*;
import ThreadChat.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class ReceiverThread implements Runnable{
    private MulticastSocket multicastSocket;
    
    ReceiverThread(MulticastSocket multicastSocket){
        this.multicastSocket = multicastSocket;
    }
    
    @Override
    public void run(){
        String message = "";
        byte[] data = new byte[256];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        try{
            do{
                multicastSocket.receive(packet);
                message = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Message from " + packet.getAddress() + ":" +
                                    message);
                Thread.yield();
            }while(true);
        }catch(IOException IOEx){
            
        }
    }
}
