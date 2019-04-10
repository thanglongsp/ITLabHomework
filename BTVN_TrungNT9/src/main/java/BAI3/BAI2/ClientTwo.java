package BAI3.BAI2;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author thanglongsp
 */
public class ClientTwo implements Runnable {

    private static Socket clientSocket = null;
    private static PrintStream oputStream = null;
    private static DataInputStream inputStream = null;
    private static BufferedReader inputLine = null;
    private static boolean closed = false;

    public static void main(String[] args) {

        int portNumber = 1996;
        String host = "localhost";

        if (args.length < 2) {
            System.out.println("Using host = " + host + ", portNumber = " + portNumber);
        } else {
            host = args[0];
            portNumber = Integer.parseInt(args[1]);
        }

        try {
            clientSocket    = new Socket(host, portNumber);
            inputLine       = new BufferedReader(new InputStreamReader(System.in));
            oputStream      = new PrintStream(clientSocket.getOutputStream());
            inputStream     = new DataInputStream(clientSocket.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + host);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to the host " + host);
        }

        if (clientSocket != null && oputStream != null && inputStream != null) {
            try {
                new Thread(new ClientTwo()).start();
                
                while (!closed)
                    oputStream.println(inputLine.readLine().trim());
                
                oputStream.close();
                inputStream.close();
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("IOException:  " + e);
            }
        }
    }

    @Override
    public void run() {
        String responseLine;
        try {
            while ((responseLine = inputStream.readLine()) != null) {
                System.out.println(responseLine);
                if (-1 != responseLine.indexOf("*** Bye")) {
                    break;
                }
            }
            closed = true;
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        }
    }
}
