/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bai4.bai1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author thanglongsp
 */
public class DBConnection {
    protected Connection conn;
    // open connect
    public void connectDB() throws ClassNotFoundException, SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbbai14", "root", "");
            System.out.println("connected !");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error in connectDB()");
        }
    }
    // close connect
    public void closeDB() throws SQLException{
        if (conn != null) {
            System.out.println("Close connection.");
            conn.close();
            conn = null;
        }
    }
}
