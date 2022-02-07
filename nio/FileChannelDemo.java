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
public class FileChannelDemo {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String path = "E:\\LeThanhPhong-60136534\\JavaNetworkProgram\\Project\\NetworkProgramCourse\\src\\nio\\data\\data.txt";
        RandomAccessFile file = new RandomAccessFile(path, "rw");
        FileChannel fileChannel = file.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(12);
        
        //Read Data
        //C1
//        fileChannel.read(buffer);
//        buffer.flip();
//        while(buffer.hasRemaining())
//            System.out.print((char) buffer.get());
    
        //C2
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
