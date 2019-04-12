package bai4.bai2;

import java.sql.Connection;
import java.sql.SQLException;

public class ThreadTwo extends Thread {
    public ThreadTwo(DBConnectionPool connectionPool, Connection conn) throws SQLException {
        conn = connectionPool.getConnection();
        System.out.println("T2 connected");
    }

    @Override
    public void run() {
    }
}
