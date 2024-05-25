package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Create {
    public static void createDatabase(String dbUrl, String user, String password) throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, user, password);
        Statement statement = connection.createStatement();
        String SQL = "CREATE DATABASE jdbc_exercise";
        statement.executeUpdate(SQL);
        System.out.println("Database created successfully!");
    }

    public static void createTable(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        String SQL = """
                     CREATE TABLE employees(
                     id INT AUTO_INCREMENT NOT NULL, 
				     username VARCHAR(255), 
                     age INTEGER, 
                     salary INTEGER,
				     PRIMARY KEY (id));            
				     """;
        statement.executeUpdate(SQL);
        System.out.println("Created table employees!");
    }
}
