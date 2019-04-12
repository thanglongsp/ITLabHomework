/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bai4.bai2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author thanglongsp
 */
public class DBConnectionPool implements Runnable {

    private String url, driver, username, password;
    private int maxConnections;
    private boolean waitIfBusy;
    private boolean connectionPending = false;
    public Vector<Connection> availableConnections, busyConnections;

    public DBConnectionPool(String driver, String url, String username, String password,
                            int initialConnections, int maxConnections, boolean waitIfBusy) throws SQLException {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
        this.maxConnections = maxConnections;
        this.waitIfBusy = waitIfBusy;

        if (initialConnections > maxConnections) {
            initialConnections = maxConnections;
        }

        availableConnections = new Vector<Connection>(initialConnections);
        busyConnections = new Vector<Connection>();

        for (int i = 0; i < initialConnections; i++) {
            availableConnections.addElement(createConnection());
        }
    }

    public Connection createConnection() throws SQLException {
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            return (connection);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public synchronized Connection getConnection() throws SQLException {
        if (!availableConnections.isEmpty()) {
            Connection existingConnection = (Connection) availableConnections.lastElement();
            int lastIndex = availableConnections.size() - 1;
            availableConnections.removeElementAt(lastIndex);

            if (existingConnection.isClosed()) {
                notifyAll();
                return (getConnection());
            } else {
                busyConnections.addElement(existingConnection);
                return (existingConnection);
            }
        } else {
            if ((totalConnections() < maxConnections) && !connectionPending) {
                makeBackgroundConnection();
            } else if (waitIfBusy) {
                System.out.println("Connection limit reached");
            }
            try {
                wait(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(DBConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public void makeBackgroundConnection() {
        connectionPending = true;
        Thread connectThread = new Thread(this);
        connectThread.start();
    }

    public synchronized void free(Connection connection) {
        busyConnections.removeElement(connection);
        availableConnections.addElement(connection);
        notifyAll();
    }

    public synchronized int totalConnections() {
        return (availableConnections.size() + busyConnections.size());
    }

    private void closeConnections(Vector<Connection> connections) {
        try {
            for (int i = 0; i < connections.size(); i++) {
                Connection connection = (Connection) connections.elementAt(i);
                if (!connection.isClosed()) {
                    connection.close();
                }
            }
        } catch (SQLException sqle) {
        }
    }

    public synchronized void closeAllConnections() {
        closeConnections(availableConnections);
    }

    @Override
    public void run() {
        try {
            Connection connection = createConnection();
            synchronized (this) {
                availableConnections.addElement(connection);
                connectionPending = false;
                notifyAll();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
