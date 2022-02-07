/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thread;

import com.sun.istack.internal.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author Dell
 */
public class MyThread implements Runnable{
    String name;
    public MyThread(String name){
       this.name = name;
    }
    @Override
    public void run(){
        for(int i = 1; i <= 10; i++){
            System.out.println(name + ": " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                java.util.logging.Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
