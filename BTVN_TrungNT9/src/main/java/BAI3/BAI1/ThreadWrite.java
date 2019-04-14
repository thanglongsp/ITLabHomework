package BAI3.BAI1;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ThreadWrite extends Thread {
    Socket socket = null;
    String name;
    boolean isRun = true;

    public ThreadWrite(Socket socket, String name) {
        this.socket = socket;
        this.name = name;
    }

    @Override
    public void run() {
        while (isRun) {
            DataOutputStream os = null;
            try {
                os = new DataOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                os.writeUTF(new Scanner(System.in).nextLine());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
