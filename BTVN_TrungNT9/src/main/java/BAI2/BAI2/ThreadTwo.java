/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BAI2.BAI2;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thanglongsp
 */
public class ThreadTwo implements Runnable {

    private LinkedList<Integer> list = new LinkedList<>();
    private final int LIMIT = 10;
    private Lock lock = new ReentrantLock();
    private Condition cond = lock.newCondition();

    ThreadTwo(LinkedList<Integer> list, Lock lock, Condition cond) {
        this.list = list;
        this.lock = lock;
        this.cond = cond;
    }

    @Override
    public void run() {
        while (true) {
            lock.lock();
            int value = list.removeFirst();
            System.out.println("Removed value by ThreadTwo is: " + value);
            System.out.println("\t Now list size is: " + list.size());
            cond.signal();
            lock.unlock();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadTwo.class.getName()).log(Level.SEVERE, null, ex);
        }
            System.out.println("ThreadTwo completed !");
    }
}
}
