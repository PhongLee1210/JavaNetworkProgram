/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thread;

import static java.lang.Thread.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Dell
 */
public class MyThread2 {
    public static void main(String[] args) throws InterruptedException {
        Thread Thread1 = new Thread(){
            public void run(){
                for(int i = 1; i <= 10; i++){
                    if(i%2 != 0)
                        System.out.println(Thread.currentThread().getName() + ": " + i);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MyThread2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        Thread Thread2 = new Thread(){
            public void run(){
                for(int i = 1; i <= 10; i++){
                    if(i%2 == 0)
                        System.out.println(Thread.currentThread().getName() + ": " + i);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MyThread2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        //YC 1
        
        Thread1.start();
        //YC 2
        //Thread1.join();
        Thread2.start();
        
    }
}
