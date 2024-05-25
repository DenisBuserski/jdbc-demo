package com.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Insert {
    public static void singleInsert(Connection connection, String username, int age, int salary) throws SQLException {
        PreparedStatement insertEmployee = connection.prepareStatement("INSERT INTO employees(username, age, salary) VALUES(?, ?, ?);");
        insertEmployee.setString(1, username);
        insertEmployee.setInt(2, age);
        insertEmployee.setInt(3, salary);
        insertEmployee.executeUpdate();
    }

    public static void multipleInsert(Connection connection) throws SQLException {
        for (int i = 1; i <= 10; i++) {
            PreparedStatement insert = connection.prepareStatement("INSERT INTO employees(username, age, salary) VALUES(?, ?, ?);");
            insert.setString(1, "Test " + i);
            insert.setInt(2, 20 + i);
            insert.setInt(3, 500 + i);
            insert.executeUpdate();

        }
    }
}
