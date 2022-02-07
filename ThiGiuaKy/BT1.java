/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThiGiuaKy;

import java.nio.ByteBuffer;

/**
 *
 * @author Dell
 */
public class BT1 {
    public static void main(String[] args) {
        String message = "Le Thanh Phong";
        Bai1_2(message);
    }
    public static void Bai1_2(String message){
        ByteBuffer bufferSrc = ByteBuffer.wrap(message.getBytes());
        ByteBuffer bufferDst = ByteBuffer.allocate(64);
        // Write BufferSrc
        bufferSrc.put(message.getBytes());
        // Read BufferSrc (Copy)
        bufferSrc.flip();
        for(int i = bufferSrc.limit() - 1 ; i >= 0; i--)
            bufferDst.put(bufferSrc.get(i));
        
        // Read bufferDst
        String result = "";
        bufferDst.flip();
        while(bufferDst.hasRemaining())
            result += (char)bufferDst.get();
        
        System.out.println(result);
        bufferSrc.clear();
        bufferDst.clear();
    }
    
    public static void Bai1_1(String message){
        ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
        // Write buffer
        buffer.put(message.getBytes());
        // Read buffer
        String result = "";
        buffer.flip();
        while(buffer.hasRemaining())
            result += (char) buffer.get();
        System.out.println(result);
        buffer.clear();
    }
    
}
