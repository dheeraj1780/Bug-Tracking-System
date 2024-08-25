package com.bts.signin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/*
 * this servlet is used as a utlity servlet for database cnonections
 */

public class DatabaseUtility {


    // Static block to load the MySQL JDBC driver
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Method to get a database connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/bug_tracking_system?autoReconnect=true&useSSL=false","root","n0vAln3t");
    }
}
