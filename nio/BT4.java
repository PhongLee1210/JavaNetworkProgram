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
public class BT4 {
    public static void main(String[] args) throws FileNotFoundException, IOException{
        String userDirpath = System.getProperty("user.dir");
        String inputpath = userDirpath + "\\src\\nio\\data\\input.txt";
        String outputpath = userDirpath + "\\src\\nio\\data\\output.txt";
        
        RandomAccessFile inputf = new RandomAccessFile(inputpath, "rw");
        RandomAccessFile outputf = new RandomAccessFile(outputpath, "rw");
        
        FileChannel inChannel = inputf.getChannel();
        FileChannel outChannel = outputf.getChannel();
        
        ByteBuffer buffer = ByteBuffer.allocate(50);
        
        while(inChannel.read(buffer) != -1)
        {
            buffer.flip();
            outChannel.write(buffer);
            buffer.clear();
        }
        inChannel.close();
        outChannel.close();
        inputf.close();
        outputf.close();
        System.out.println("Done.");
    }
}
