package BAI3.BAI1;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadRead extends Thread {
    Socket socket = null;
    String name;
    private final AtomicBoolean running = new AtomicBoolean(true);

    public ThreadRead(Socket socket, String name) {
        this.socket = socket;
        this.name = name;
    }

    public void shutdown() throws InterruptedException {
        Thread.sleep(1000);
        Thread.currentThread().interrupt();
    }

    @Override
    public void run() {
        while (running.get()) {
            DataInputStream is = null;
            try {
                is = new DataInputStream(socket.getInputStream());
                System.out.println("From " + name + ":" + is.readUTF());
            } catch (IOException e) {
                running.set(false);
                System.out.println("disconnect ...");
                try {
                    shutdown();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
