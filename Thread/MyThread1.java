/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thread;

import static java.lang.Thread.MAX_PRIORITY;
import static java.lang.Thread.MIN_PRIORITY;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
class CountThread implements Runnable{
    @Override
    public void run() {
        for(int i = 1; i <= 10; i++){
            System.out.println(Thread.currentThread().getName() + ": " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(CountThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
public class MyThread1 {
    public static void main(String[] args) {
        CountThread count1To10 = new CountThread();
        Thread Thread1 = new Thread(count1To10);
        Thread Thread2 = new Thread(count1To10);
        
        Thread1.setPriority(MAX_PRIORITY);
        Thread2.setPriority(MIN_PRIORITY);
        
        Thread1.start();
        Thread2.start();
        
    }
}
