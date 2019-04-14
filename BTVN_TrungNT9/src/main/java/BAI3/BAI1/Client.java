/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BAI3.BAI1;

import java.net.Socket;

/**
 * @author thanglongsp
 */
public class Client {

    /**
     * @param argv
     * @throws java.lang.Exception
     */
    public static void main(String argv[]) throws Exception {

        Socket clientSocket = new Socket("localhost", 1996);
        System.out.println("Connected !");

        ThreadRead threadRead = new ThreadRead(clientSocket, "Server");
        threadRead.start();

        ThreadWrite threadWrite = new ThreadWrite(clientSocket, "Client");
        threadWrite.start();
    }
}
