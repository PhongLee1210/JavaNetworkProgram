/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThreadChat;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class Server {
    public static void main(String[] args) throws IOException {
         handlerClients handlerClient = new handlerClients();
         Thread handlerClientThread = new Thread(handlerClient);
         handlerClientThread.setDaemon(true);
         handlerClientThread.start();
         
         //Broadcast message to all clients
         String message = "";
         Scanner input = new Scanner(System.in);
         do{
             message = input.nextLine();
             handlerClient.broadcast(message);
         }while(!message.equals("q"));
         System.out.println("Done.");
    }
}
