/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculator;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 *
 * @author Dell
 */
public class Server {
    public static void main(String[] args) {
        InetSocketAddress address = new InetSocketAddress(6969);
        System.out.println("Opening port...\n");
        try(ServerSocketChannel server = ServerSocketChannel.open()){
            server.bind(address);
            
            chatClient(server);
            
        }catch(IOException ioEx){
            System.err.println("I/O error: " + ioEx.getMessage());
        }
    }
    private static void chatClient(ServerSocketChannel server){
            try(SocketChannel channel = server.accept()){
                System.out.println("Received connection from " + channel.getRemoteAddress());
                System.out.println("Waitting message from client...");
                
                double a = Double.parseDouble(ControllerMethod.receiveMessage(channel));
                double b = Double.parseDouble(ControllerMethod.receiveMessage(channel));
                String calculation = ControllerMethod.receiveMessage(channel);
                
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
                server.close();
 
            }catch(IOException ioEx){
                System.err.println("I/O error: " + ioEx.getMessage());
            }
        }
}
