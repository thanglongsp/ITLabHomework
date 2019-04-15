import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class RunPool implements Runnable {
    private int id;

    public RunPool(int id) {
        this.id = id;
    }

    public void run() {
        System.out.println("Handling : " + id);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Done : " + id);
    }
}

public class ThreadPoolExample {
    public static void main(String[] args) {
        LinkedBlockingQueue<Runnable> q = new LinkedBlockingQueue<Runnable>(100);
        ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 7, 1, TimeUnit.SECONDS, q);

        for (int i = 0; i < 20; i++) {
            pool.execute(new RunPool(i));
        }
    }
}