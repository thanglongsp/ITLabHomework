/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BAI3.BAI1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author thanglongsp
 */
public class TCPServer {

    /**
     * @param argv
     * @throws java.lang.Exception
     */
    public static void main(String argv[]) throws Exception {
        String send;
        String receive;

        ServerSocket welcomeSocket = new ServerSocket(6789);
        Socket connectionSocket = welcomeSocket.accept();
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

        while (true) {
            System.out.println("waiting reply ...  ");
            receive = inFromClient.readLine();
            System.out.println("Received from client : " + receive);
            System.out.print("Send to client : ");

            Scanner sc1 = new Scanner(System.in);
            send = sc1.nextLine();
            outToClient.writeBytes(send.toUpperCase() + '\n');
        }
    }
}