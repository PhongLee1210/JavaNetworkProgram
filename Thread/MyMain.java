/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thread;

/**
 *
 * @author Dell
 */
public class MyMain {
    public static void main(String[] args) throws InterruptedException {
        Runnable r1 = new MyThread("Phong");
        Runnable r2 = new MyThread("My Nigga");
        Thread st1 = new Thread(r1);
        Thread st2 = new Thread(r2);
        st1.setPriority(Thread.MIN_PRIORITY);
        st2.setPriority(Thread.MAX_PRIORITY);
        st1.start();
        st1.join();
        st2.start();
        
    }
}
