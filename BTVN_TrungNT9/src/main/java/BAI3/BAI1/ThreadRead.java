package BAI3.BAI1;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ThreadRead extends Thread {
    Socket socket = null;
    String name;

    public ThreadRead(Socket socket, String name) {
        this.socket = socket;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            DataInputStream is = null;
            try {
                is = new DataInputStream(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                System.out.println("From " + name + ":" + is.readUTF());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
