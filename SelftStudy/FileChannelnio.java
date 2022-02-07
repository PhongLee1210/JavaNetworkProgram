/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SelftStudy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
/**
 *
 * @author Dell
 */
public class FileChannelnio {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String userDirpath = System.getProperty("user.dir");
        String input0path = userDirpath + "\\src\\nio\\data\\input.txt";
        String input1path = userDirpath + "\\src\\nio\\data\\input1.txt";
        String outputpath = userDirpath + "\\src\\nio\\data\\output.txt";
        
        RandomAccessFile input0f = new RandomAccessFile(input0path, "rw");
        RandomAccessFile input1f = new RandomAccessFile(input1path, "rw");
        RandomAccessFile outputf = new RandomAccessFile(outputpath, "rw");
        
        FileChannel in0Channel = input0f.getChannel();
        FileChannel in1Channel = input1f.getChannel();
        
        FileChannel outChannel = outputf.getChannel();
        
        ByteBuffer buffer = ByteBuffer.allocate(32);

        while(in0Channel.read(buffer) > 0 || in1Channel.read(buffer) > 0)
        {
            buffer.flip();
            outChannel.write(buffer);
            buffer.clear();
        }
        in0Channel.close();
        in1Channel.close();
        outChannel.close();
        input0f.close();
        input1f.close();
        outputf.close();

    }
}

