public class DeadLock implements Runnable {

    private synchronized void test1(){
        System.out.println("Test1");
        test2();
    }

    private synchronized void test2(){
        System.out.println("Test2");
        test1();
    }

    public void run() {
        test1();
    }

    public static void main(String[] args) {
        DeadLock dl = new DeadLock();
        Thread t1 = new Thread(dl);
        Thread t2 = new Thread(dl);

        t1.start();
        t2.start();
    }
}
