import java.util.Scanner;

class MyThread extends Thread {
    private static boolean flag;

    static {
        flag = true;
    }

    @Override
    public void run() {
        while (flag) {
            try {
                MyThread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(currentThread().getName());
        }
    }

    void shutdown() {
        flag = false;
    }
}

class MyThread1 extends Thread {
    private static boolean flag1;

    static {
        flag1 = true;
    }

    @Override
    public void run() {
        while (flag1) {
            try {
                MyThread1.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(currentThread().getName());
        }
    }

    void shutdown() {
        flag1 = false;
    }
}

public class Shutdown {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        MyThread1 t2 = new MyThread1();
        t2.setName("Thread 2");
        t1.start();
        t2.start();
        System.out.println("Enter 1 to shutdown");
        (new Scanner(System.in)).nextInt();
        t1.shutdown();
        System.out.println("Enter 2 to shutdown 2");
        (new Scanner(System.in)).nextInt();
        t2.shutdown();
    }
}
