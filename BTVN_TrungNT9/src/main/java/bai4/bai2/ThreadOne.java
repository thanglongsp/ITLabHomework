package bai4.bai2;

import java.sql.Connection;
import java.sql.SQLException;

public class ThreadOne extends Thread {

    public ThreadOne(DBConnectionPool connectionPool,Connection conn) throws SQLException {
        conn = connectionPool.getConnection();
        System.out.println("T1 connected");
    }

    @Override
    public void run() {
    }
}
