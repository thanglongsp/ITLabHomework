package bai4.bai2;

import java.sql.Connection;
import java.sql.SQLException;

public class ThreadHandle extends Thread {

    public ThreadHandle(DBConnectionPool connectionPool, Connection conn) throws SQLException {
        conn = connectionPool.getConnection();
        System.out.println("Connected");
    }

    @Override
    public void run() {
    }
}