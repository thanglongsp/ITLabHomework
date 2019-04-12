/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bai4.bai2;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author thanglongsp
 */
public class Main {
    public static void main(String[] args) throws SQLException, InterruptedException {
        DBConnectionPool connectionPool = new DBConnectionPool("com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://localhost:3306/dbbai14", "root", "", 1, 2, true);
        Connection conn = connectionPool.getConnection();

        ThreadOne t1 = new ThreadOne(connectionPool, conn);
        ThreadTwo t2 = new ThreadTwo(connectionPool, conn);
        ThreadThree t3 = new ThreadThree(connectionPool, conn);

        t1.start();
        t2.start();
        t3.start();

        connectionPool.free(conn);
        connectionPool.closeAllConnections();
    }
}