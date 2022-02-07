/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thread;

import static java.lang.Thread.MAX_PRIORITY;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
class Count{
    int i = 1;
    public synchronized void count1To10(int n) throws InterruptedException{
        while(i <= n){
            System.out.println(Thread.currentThread().getName() + ": " + i);
            wait();
            Thread.sleep(500);
            i++;
            notify();
        }
    }
    public synchronized void ChanLe(int n) throws InterruptedException{
        while(i <= n){
            if(i%2 != 0)
                System.out.println(Thread.currentThread().getName() + ": " + "Lẽ");
            else System.out.println(Thread.currentThread().getName() + ": " + "Chẵn");
            
            notify();
            wait();
        }
    }
}
public class MyThread3 {
        public static void main(String[] args) {
            int n = 10;
            Count c = new Count();
            Thread thread1 = new Thread(){
                public void run(){
                    try {
                        c.count1To10(n);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MyThread3.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
            Thread thread2 = new Thread(){
                public void run(){
                    try {
                        c.ChanLe(n);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MyThread3.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
            thread1.setPriority(MAX_PRIORITY);
            thread1.start();
            thread2.start();
        }
            
}

