import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Synchronization {
    private static List<Integer> list1;
    private static List<Integer> list2;

    static {
        list1 = new ArrayList<Integer>();
        list2 = new ArrayList<Integer>();
    }

    private Random rd;

    {
        rd = new Random();
    }

    private synchronized void setList1() {
        list1.add(rd.nextInt(1000));
    }

    private synchronized void setList2() {
        list2.add(rd.nextInt(1000));
    }

    private void handle() {
        for (int i = 0; i < 1000; i++) {
            setList1();
            setList2();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        final Synchronization syn = new Synchronization();
        System.out.println("Handling ...");

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                syn.handle();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                syn.handle();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("List1'size : " + list1.size() + " List2'size : " + list2.size());
    }
}
