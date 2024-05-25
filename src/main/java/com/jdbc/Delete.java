package com.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete {
    public static void delete(Connection connection, int id) throws SQLException {
        PreparedStatement deleteEmployee = connection.prepareStatement("DELETE FROM employees WHERE id = ?");
        deleteEmployee.setInt(1, id);
        deleteEmployee.executeUpdate();
    }

    public static void deleteFromConnectedTables(Connection connection, int manager_id) throws SQLException {
        connection.setAutoCommit(false);

        try {
            PreparedStatement deleteManagersEmployees = connection.prepareStatement("DELETE FROM managers_employees WHERE manager_id = ?;");
            deleteManagersEmployees.setInt(1, manager_id);
            deleteManagersEmployees.executeUpdate();

            PreparedStatement deleteManager = connection.prepareStatement("DELETE FROM managers WHERE id = ?;");
            deleteManager.setInt(1, manager_id);
            deleteManager.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        }
    }
}
