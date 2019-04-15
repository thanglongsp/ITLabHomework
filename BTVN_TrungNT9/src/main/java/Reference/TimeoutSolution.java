package Reference;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TimeoutSolution {

    public static void main(String args[]) throws Exception {
        TimeoutSolution solution = new TimeoutSolution();
        solution.executeAndWaitUtilTimeout();
    }

    private void executeAndWaitUtilTimeout() throws Exception {
        Thread2 t2 = new Thread2(2000, TimeUnit.MILLISECONDS);
        Thread1 t1 = new Thread1(t2);
        Thread running1 = (new Thread(t1));
        running1.start();
        t2.setInterruptedThread(running1);
        (new Thread(t2)).start();
    }

    class Thread1 implements Runnable {
        private Thread2 triggerThread;

        public Thread1(Thread2 triggerThread) {
            this.triggerThread = triggerThread;
        }

        @Override
        public void run() {
            try {
                System.out.println("Thread 1 run at:" + new Date());
                Thread.sleep(1000);
                triggerThread.releaseLock();
                System.out.println("Thread 1 done at:" + new Date());
            } catch (Exception e) {
                System.out.println("Thread force done");
            }
        }

    }

    class Thread2 implements Runnable {
        private long timeout;
        private TimeUnit timeUnit;
        private ReentrantLock lock = new ReentrantLock();
        private Thread interruptedThread;
        private Condition canRun;
        private AtomicBoolean isRun = new AtomicBoolean(false);

        public Thread2(long timeout, TimeUnit timeUnit) {
            this.timeout = timeout;
            this.timeUnit = timeUnit;
            canRun = lock.newCondition();
        }

        public void setInterruptedThread(Thread interruptedThread) {
            this.interruptedThread = interruptedThread;
        }

        @Override
        public void run() {
            try {
                final ReentrantLock lock = this.lock;
                long nanos = timeUnit.toNanos(timeout);
                lock.lockInterruptibly();
                System.out.println("Thread 2 start on:" + new Date());
                for (; ; ) {
                    if (nanos <= 0)
                        break;
                    if (isRun.get())
                        break;
                    nanos = canRun.awaitNanos(nanos);
                }
                if (interruptedThread != null && interruptedThread.isAlive())
                    interruptedThread.interrupt();
                System.out.println("Thanks alot on thread 2:" + new Date());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void releaseLock() throws InterruptedException {
            try {
                final ReentrantLock lock = this.lock;
                lock.lockInterruptibly();
                canRun.signal();
                isRun.set(true);
            } finally {
                lock.unlock();
            }
        }
    }
}
