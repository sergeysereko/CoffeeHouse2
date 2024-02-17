package org.example;


import org.example.cafemanager.CafeManager;
import org.example.dao.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;

public class CafeManagementSystem {
    public static void main(String[] args) {
        try (Connection connection = DatabaseManager.getConnection()) {
            CafeManager.manageCafe(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
