package bai4.bai2;

import java.sql.Connection;
import java.sql.SQLException;

public class ThreadThree extends Thread {
    private DBConnectionPool connectionPool;
    private Connection conn;

    public ThreadThree(DBConnectionPool connectionPool, Connection conn) throws SQLException {
        conn = connectionPool.getConnection();
        System.out.println("T3 connected");
    }

    @Override
    public void run() {
    }
}
