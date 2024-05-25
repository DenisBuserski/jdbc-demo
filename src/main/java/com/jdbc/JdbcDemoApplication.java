package com.jdbc;


import java.sql.*;
import java.util.Arrays;
import java.util.Properties;
import java.util.stream.Collectors;

import static com.jdbc.Create.createDatabase;
import static com.jdbc.Create.createTable;
import static com.jdbc.Delete.delete;
import static com.jdbc.Delete.deleteFromConnectedTables;
import static com.jdbc.Insert.multipleInsert;
import static com.jdbc.Insert.singleInsert;
import static com.jdbc.Select.select;
import static com.jdbc.Select.selectWhereIn;

public class JdbcDemoApplication {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASSWORD = "denis";

    public static void main(String[] args) throws SQLException {
        // createDatabase(DB_URL, USER, PASSWORD);

        Connection connection = getConnection(USER, PASSWORD, DB_URL);

        // createTable(connection);

        // singleInsert(connection, "Denis", 100, 100);
        // multipleInsert(connection);

        // select(connection);
        // selectWhereIn(connection, new String[]{"1", "3", "5"});

        // update(connection, 1);

        // delete(connection, 9);

        // storedProcedure(connection, 1);

        // In order to test this method please execute data.sql before that.
        // deleteFromConnectedTables(connection, 3);

        connection.close();
    }

    private static Connection getConnection(String user, String password, String dbUrl) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", user); // username
        properties.setProperty("password", password); // password
        return DriverManager.getConnection(dbUrl + "jdbc_exercise", properties);
    }

    private static void update(Connection connection, int id) throws SQLException {
        PreparedStatement update = connection.prepareStatement("""
                UPDATE employees
                SET age = age + 40
                WHERE id = ?
                """);
        update.setInt(1, id);
        update.executeUpdate();
    }

    private static void storedProcedure(Connection connection, int id) throws SQLException {
        String storedProcedureSQL = """
                CREATE PROCEDURE usp_get_older(IN id INT)
                BEGIN
                    UPDATE employees
                    SET age = age + 1
                    WHERE employees.id = id;
                END
                """;

        Statement statement = connection.createStatement();
        statement.execute(storedProcedureSQL);
        System.out.println("Stored procedure created successfully.");


        CallableStatement statement1 = connection.prepareCall("call usp_get_older(?)");
        statement1.setInt(1, id);
        statement1.execute();
    }

}