/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("CLIENT");
        DatagramSocket client = new DatagramSocket();
        SocketAddress address = new InetSocketAddress("localhost", 6969);

        Scanner input = new Scanner(System.in);
        String message = "";
        while(true){
            System.out.print("Send: ");   message = input.nextLine();

            byte[] buffer = message.getBytes(StandardCharsets.UTF_8);
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address);
            client.send(packet);

            byte[] recBuffer = new byte[1024];
            DatagramPacket recPacket = new DatagramPacket(recBuffer, recBuffer.length);
            client.receive(recPacket);

            String recMessage = new String(recPacket.getData(), StandardCharsets.UTF_8);
            System.out.println("SERVER> "+ recMessage);

            if(message.equals("q")) break;
        }
        System.out.println("Done.");

    }
}

