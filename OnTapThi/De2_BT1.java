/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnTapThi;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class De2_BT1 {
    public static void main(String[] args) {
        try {
            Bai1_2();
        } catch (IOException IoEx) {
            System.err.println("Error I/O: " + IoEx);
        }
    }
    public static void Bai1_2() throws IOException{
        String path1 = "E:\\LeThanhPhong-60136534\\JavaNetworkProgram\\Project\\NetworkProgramCourse\\src\\OnTapThi\\serverfile.txt";
        String path2 = "E:\\LeThanhPhong-60136534\\JavaNetworkProgram\\Project\\NetworkProgramCourse\\src\\OnTapThi\\clientfile.txt";
        
        RandomAccessFile file1 = new RandomAccessFile(path1, "rw");
        FileChannel fChannel1 = file1.getChannel();
        FileChannel fChannel2 = FileChannel.open(Paths.get(path2), StandardOpenOption.APPEND);
        ByteBuffer buffer = ByteBuffer.allocate(128);
        
        while(fChannel1.read(buffer) != -1){
            buffer.flip();
            fChannel2.write(buffer);
            buffer.clear();
        }
        fChannel1.close();
        fChannel2.close();
        file1.close();
        System.out.println("Done");

    }
}
