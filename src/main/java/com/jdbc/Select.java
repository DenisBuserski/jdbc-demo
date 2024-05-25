package com.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Select {
    public static void select(Connection connection) throws SQLException {
        String salary = "505";
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees WHERE salary > ?");
        statement.setDouble(1, Double.parseDouble(salary));

//        String age = scanner.nextLine();
//        PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees WHERE age > ?");
//        statement.setInt(1, Integer.parseInt(age));

//        String username = scanner.nextLine();
//        PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees WHERE username = ?");
//        statement.setString(1, username);

        ResultSet resultSet = statement.executeQuery(); // Get result
        while (resultSet.next()) {
            String username = resultSet.getString("username"); // Get username
            int age = resultSet.getInt("age"); // Get age
            // int age = resultSet.getInt(3); // Give me the 3th column
            System.out.println(username + " " + age); // Print
        }
    }

    public static void selectWhereIn(Connection connection, String [] input) throws SQLException {
        String ids = Arrays.stream(input).collect(Collectors.joining(","));
        String statement = String.format("SELECT" +
                                         " username," +
                                         " age," +
                                         " salary " +
                                         " FROM employees" +
                                         " WHERE id IN (%s)", ids);

        PreparedStatement prepareStatement = connection.prepareStatement(statement);
        ResultSet resultSet = prepareStatement.executeQuery();
        while (resultSet.next()) {
            String username = resultSet.getString("username");
            int age = resultSet.getInt("age");
            int salary = resultSet.getInt("salary");
            System.out.println(username + " " + age + " " + salary);
        }
    }
}
