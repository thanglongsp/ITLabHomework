/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BAI3.BAI1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author thanglongsp
 */
public class TCPClient {

    /**
     * @param argv
     * @throws java.lang.Exception
     */
    public static void main(String argv[]) throws Exception {
        String send;
        String receive;

        Socket clientSocket = new Socket("localhost", 6789);
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        while (true) {
            System.out.print("Send to server : ");
            send = inFromUser.readLine();
            outToServer.writeBytes(send + '\n');

            System.out.println("waiting reply ... ");
            receive = inFromServer.readLine();
            System.out.println("FROM SERVER: " + receive.toLowerCase());
        }
    }
}
