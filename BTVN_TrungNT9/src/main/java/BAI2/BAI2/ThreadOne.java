/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BAI2.BAI2;

import java.util.Date;
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
public class ThreadOne implements Runnable {

    private LinkedList<Integer> list = new LinkedList<>();
    private final int LIMIT = 1000000;
    private Lock lock = new ReentrantLock();
    private Condition cond = lock.newCondition();
    Date start = new Date();
    int time;

    ThreadOne(LinkedList<Integer> list, Lock lock, Condition cond) {
        this.list = list;
        this.lock = lock;
        this.cond = cond;
        start = new Date();
        time = 0;
    }

    @Override
    public void run() {
        int value = 0;
        while (true) {
            lock.lock();
            list.add(value);
            System.out.println("ThreadHandle added: " + value + " queue size is " + list.size());
            value++;
            cond.signal();
            Date now = new Date();
            time += now.compareTo(start);
            System.out.println(time);
            if (time > 4) {
                System.out.println("Interupted Thread Handle");
                try {
                    cond.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ThreadOne.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
        }
    }
}
