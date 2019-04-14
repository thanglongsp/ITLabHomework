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
 * @author thanglongsp
 */
public class Server {

    /**
     * @param argv
     * @throws java.lang.Exception
     */
    public static void main(String argv[]) throws Exception {
        ServerSocket welcomeSocket = new ServerSocket(1996);
        System.out.println("Waiting Client ...");
        Socket connectionSocket = welcomeSocket.accept();
        System.out.println("Connected !");

        ThreadRead threadRead = new ThreadRead(connectionSocket, "Client");
        threadRead.start();

        ThreadWrite threadWrite = new ThreadWrite(connectionSocket, "Server");
        threadWrite.start();
    }
}