package BAI3.BAI1;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadWrite extends Thread {
    Socket socket = null;
    String name;
    private final AtomicBoolean running = new AtomicBoolean(true);

    public ThreadWrite(Socket socket, String name) {
        this.socket = socket;
        this.name = name;
    }

    public void shutdown() throws InterruptedException {
        ThreadRead.sleep(1000);
        running.set(false);
    }

    @Override
    public void run() {
        while (running.get()) {
            DataOutputStream os = null;
            try {
                os = new DataOutputStream(socket.getOutputStream());
                os.writeUTF(new Scanner(System.in).nextLine());
            } catch (IOException e) {
                try {
                    shutdown();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                e.printStackTrace();
            }
        }
    }
}
