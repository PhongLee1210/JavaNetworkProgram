/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nio;
import java.nio.CharBuffer;
/**
 *
 * @author Dell
 */
public class NIO_Demo {
    public static void main(String[] args) {
        CharBuffer buffer = CharBuffer.allocate(1024);
        buffer.append("Lap Trinh Mang");
        //a
        System.out.println("=== Write Mode ===");
        bufferInfo(buffer);
        
        //b
        char[] backingArray = buffer.array();
        System.out.println("Backing Array size: " + backingArray.length);
        
        //c
        buffer.flip();
        System.out.println("=== Read Mode ===");
        bufferInfo(buffer);
        //C1
        
//        for (int i = 0; i < buffer.limit(); i++)
//            builder.append(buffer.get());
//        bufferInfo(buffer);
        //C2
//        while(buffer.hasRemaining())
//            builder.append(buffer.get());
//        System.out.println("Data: " + builder.toString());

        StringBuilder builder = new StringBuilder();
        StringBuilder hello = new StringBuilder("Co gang dung ngu guc");
        
        buffer.compact();
        buffer.append(hello);
//        for (int i = 0; i < hello.length(); i++)
//            buffer.put(hello.charAt(i));
        
        buffer.flip();
    
        while(buffer.hasRemaining())
            builder.append(buffer.get());
        
        System.out.println("Data: " + builder.toString());
    }
    
    // Print info about buffer
    private static void bufferInfo(CharBuffer buffer){
        System.out.println("position: " + buffer.position()
                         + "; limit: "  + buffer.limit()
                         + "; capacity: "  + buffer.capacity());
    }
}
