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
public class FillingAndDrainning {
    public static void main(String[] args) {
        String[] poem =
            {
                "Roses are red",
                "Violets are blue",
                "Sugar is sweet",
                "And so are you."
            };
        CharBuffer buffer = CharBuffer.allocate(20);
        //Each line of poem
        for(int i = 0; i < poem.length; i++){
            // Filling each char of poem lines to buffer
            for (int j = 0; j < poem[i].length(); j++)
                buffer.put(poem[i].charAt(j));
            // Flip buffer 
            buffer.flip();
            // Drain buffer
            while(buffer.hasRemaining())
                System.out.print(buffer.get());
            buffer.clear();
            System.out.println();
        }
     
    }
}
