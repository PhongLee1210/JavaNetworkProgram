/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *
 * @author Dell
 */
public class BT2 {
    static String[] poem =
    {
        "Roses are red",
        "Violets are blue",
        "Sugar is sweet",
        "And so are you."
    };
    public static void main(String[] args) throws IOException{
        String path = "E:\\LeThanhPhong-60136534\\JavaNetworkProgram\\Project\\NetworkProgramCourse\\src\\nio\\data\\output.txt";
        RandomAccessFile file = new RandomAccessFile(path, "rw");
        FileChannel fileChannel = file.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(32);
        //Each line of poem
        for(int i = 0; i < poem.length; i++){
            // Filling buffer poem[i]
            buffer.put(poem[i].getBytes());
            buffer.flip();
            fileChannel.write(buffer);
            buffer.clear();
            file.writeBytes("\n");
        }
        while(fileChannel.read(buffer) > 0){
            buffer.flip();
            
            while(buffer.hasRemaining())
                System.out.print((char) buffer.get());
            
            buffer.clear();
        }
        fileChannel.close();
        file.close();
        System.out.println("Done.");

    }
}
