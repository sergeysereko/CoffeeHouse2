package org.example.cafemanager;

import org.example.dao.DatabaseManager;
import org.example.dao.customerDAO.CustomerDaoImpl;
import org.example.dao.dessertDAO.DessertDaoImpl;
import org.example.dao.drinkDAO.DrinkDaoImpl;
import org.example.dao.orderDAO.OrderDaoImpl;
import org.example.dao.orderItemDAO.OrderItemDaoImpl;
import org.example.dao.scheduleDAO.ScheduleDaoImpl;
import org.example.dao.staffDAO.StaffDaoImpl;
import org.example.model.*;

import java.sql.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CafeManager {
    public static void manageCafe(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите, что вы хотите сделать?:");
            System.out.println("1. Показать минимальную скидку для клиента");
            System.out.println("2. Показать максимальную скидку для клиента");
            System.out.println("3. Показать клиентов с минимальной скидкой и величину скидки");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addNewDrink(connection, scanner);
                    break;
                case 2:
                    addNewDessert(connection, scanner);
                    break;
                case 3:
                    addNewBarista(connection, scanner);
                    break;
                // и так далее для остальных опций меню...
                case 0:
                    System.out.println("Программа завершена.");
                    System.exit(0);
                default:
                    System.out.println("Некорректный выбор. Пожалуйста, выберите снова.");
            }
        }
    }

    private static void addNewDrink(Connection connection, Scanner scanner) throws SQLException {
        // Логика добавления нового напитка
    }

    private static void addNewDessert(Connection connection, Scanner scanner) throws SQLException {
        // Логика добавления нового десерта
    }

    private static void addNewBarista(Connection connection, Scanner scanner) throws SQLException {
        // Логика добавления нового бариста
    }

    // Остальные методы, которые были в классе CafeManagementSystem, также можно перенести сюда
}
