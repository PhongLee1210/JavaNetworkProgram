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
public class BT5 {
    public static void main(String[] args) {
        String userDirpath = System.getProperty("user.dir");
        String input0path = userDirpath + "\\src\\nio\\data\\input.txt";
        String input1path = userDirpath + "\\src\\nio\\data\\input1.txt";
        String outputpath = userDirpath + "\\src\\nio\\data\\output.txt";
        try{
            RandomAccessFile input0f = new RandomAccessFile(input0path, "rw");
            RandomAccessFile input1f = new RandomAccessFile(input1path, "rw");
            RandomAccessFile outputf = new RandomAccessFile(outputpath, "rw");

            FileChannel in0Channel = input0f.getChannel();
            FileChannel in1Channel = input1f.getChannel();
            FileChannel outChannel = outputf.getChannel();
            
            ByteBuffer buffer = ByteBuffer.allocate(20);
        
            while(in0Channel.read(buffer) != -1 || in1Channel.read(buffer) != -1)
            {
                buffer.flip();
                outChannel.write(buffer);
                buffer.clear();
            }
            in0Channel.close(); in1Channel.close();  outChannel.close();
            input0f.close();    input1f.close();    outputf.close();
            
            System.out.println("Done.");
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
