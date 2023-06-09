package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    public static Connection getCon() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/oop3", "userjon",
                    "12345");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }
}
