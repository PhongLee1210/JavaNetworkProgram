/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SelftStudy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *
 * @author Dell
 */
public class ScatterAndGather {
    static String[] poem =
    {
        "Roses are red",
        "Violets are blue",
        "Sugar is sweet",
        "And so are you."
    };
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String outputpath = "E:\\LeThanhPhong-60136534\\JavaNetworkProgram\\Project\\NetworkProgramCourse\\src\\nio\\data\\output.txt";
        RandomAccessFile outputf = new RandomAccessFile(outputpath, "rw");
        FileChannel outChannel = outputf.getChannel();
        
        ByteBuffer buffer1 = ByteBuffer.allocate(32);
        ByteBuffer buffer2 = ByteBuffer.allocate(32);
        int i = 0;
        for (i = 0; i < poem[0].length(); i++)
           buffer1.put((byte) poem[0].charAt(i));
        for (i = 0; i < poem[1].length(); i++)
           buffer2.put((byte) poem[1].charAt(i));
        //write data into channel
        buffer1.flip();
        buffer2.flip();
        ByteBuffer[] bufferArray = { buffer1, buffer2 };
        outChannel.write(bufferArray);
        System.out.println("Done.");
    }
}
