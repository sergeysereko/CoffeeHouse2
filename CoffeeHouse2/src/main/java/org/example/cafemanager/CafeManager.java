package org.example.cafemanager;


import org.example.dao.customerDAO.CustomerDao;
import org.example.dao.customerDAO.CustomerDaoImpl;
import org.example.dao.orderDAO.OrderDaoImpl;
import org.example.dao.orderItemDAO.OrderItemDaoImpl;
import org.example.dao.scheduleDAO.ScheduleDaoImpl;
import org.example.dao.staffDAO.StaffDaoImpl;
import org.example.model.Customer;
import org.example.model.Order;
import org.example.model.Schedule;
import org.example.model.Staff;


import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
            System.out.println("4. Показать клиентов с максимальной скидкой и величину скидки");
            System.out.println("5. Показать среднюю величину скидки");
            System.out.println("6. Показать самого молодого клиента;");
            System.out.println("7. Показать самого возрастного клиента;");
            System.out.println("8. Показать клиентов, у которых день рождения в этот день;");
            System.out.println("9. Показать клиентов, у которых не заполнен контактный почтовый адрес;");
            System.out.println("10. Показать информацию о заказах в конкретную дату;");
            System.out.println("11. Показать информацию о заказах в указанном промежутке дат;");
            System.out.println("12. Показать количество заказов десертов в конкретную дату;");
            System.out.println("13. Показать количество заказов напитков в конкретную дату;");
            System.out.println("14. Показать информацию о клиентах, которые заказывали напитки сегодня;");
            System.out.println("15. Показать среднюю сумму заказа в конкретную дату;");
            System.out.println("16. Показать максимальную сумму заказа в конкретную дату;");
            System.out.println("17. Показать клиента, который совершил максимальную сумму заказа в конкретную дату");
            System.out.println("18.Показать расписание работы конкретного бариста на неделю;");
            System.out.println("19.Показать расписание работы всех бариста на неделю;");
            System.out.println("20.Показать расписание работы для всех работников кафе на неделю.");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    minimumCustomerDiscount(connection, scanner);
                    break;
                case 2:
                    maximumCustomerDiscount(connection, scanner);
                    break;
                case 3:
                    customersWithMinimumDiscount(connection, scanner);
                    break;
                case 4:
                    customersWithMaximumDiscount(connection, scanner);
                    break;
                case 5:
                    averageDiscount(connection, scanner);
                    break;
                case 6:
                    youngestCustomer(connection, scanner);
                    break;
                case 7:
                    oldestCustomer(connection, scanner);
                    break;
                case 8:
                    customersWithBirthdayToday(connection, scanner);
                    break;
                case 9:
                    CustomersWithNoEmail(connection, scanner);
                    break;
                case 10:
                    showOrdersByDate(connection, scanner);
                    break;
                case 11:
                    showOrdersBetweenDates(connection, scanner);
                    break;
                case 12:
                    showDessertOrdersCountByDate(connection, scanner);
                    break;
                case 13:
                    showBeverageOrdersCountByDate(connection, scanner);
                    break;
                case 14:
                    showCustomersAndBaristasForBeverageOrdersToday(connection, scanner);
                    break;
                case 15:
                    averageOrderAmountByDate(connection, scanner);
                    break;
                case 16:
                    maxOrderAmountByDate(connection, scanner);
                    break;
                case 17:
                    customerWithMaxOrderAmountByDate(connection, scanner);
                    break;
                case 18:
                    showBaristaScheduleForWeek(connection, scanner);
                    break;
                case 19:
                    showAllBaristasScheduleForWeek(connection, scanner);
                    break;
                case 20:
                    showScheduleForAllStaffByWeek(connection);
                    break;

                case 0:
                    System.out.println("Программа завершена.");
                    System.exit(0);
                default:
                    System.out.println("Некорректный выбор. Пожалуйста, выберите снова.");
            }
        }
    }

    private static void minimumCustomerDiscount(Connection connection, Scanner scanner) throws SQLException {
        CustomerDao customerDao = new CustomerDaoImpl(connection);
        double minDiscount = customerDao.findMinimumDiscount();
        System.out.println("Минимальная скидка для клиента: " + minDiscount);
    }

    private static void maximumCustomerDiscount(Connection connection, Scanner scanner) throws SQLException {
        CustomerDao customerDao = new CustomerDaoImpl(connection);
        double maxDiscount = customerDao.findMaximumDiscount();
        System.out.println("Максимальная скидка для клиента: " + maxDiscount);
    }

    private static void customersWithMinimumDiscount(Connection connection, Scanner scanner) throws SQLException {
        CustomerDao customerDao = new CustomerDaoImpl(connection);
        List<Customer> customers = customerDao.findCustomersWithMinimumDiscount();
        System.out.println("Клиенты с минимальной скидкой:");
        for (Customer customer : customers) {
            System.out.println("Имя: " + customer.getFullName() + ", Скидка: " + customer.getDiscount());
        }
    }

    private static void customersWithMaximumDiscount(Connection connection, Scanner scanner) throws SQLException {
        CustomerDao customerDao = new CustomerDaoImpl(connection);

        List<Customer> customers = customerDao.findCustomersWithMaximumDiscount();

        System.out.println("Клиенты с максимальной скидкой:");
        for (Customer customer : customers) {
            System.out.println("Имя: " + customer.getFullName() + ", Скидка: " + customer.getDiscount());
        }
    }

    private static void averageDiscount(Connection connection, Scanner scanner) throws SQLException {
        CustomerDao customerDao = new CustomerDaoImpl(connection);

        double averageDiscount = customerDao.findAverageDiscount();

        System.out.println("Средняя величина скидки: " + averageDiscount);
    }

    private static void youngestCustomer(Connection connection, Scanner scanner) throws SQLException {
        CustomerDao customerDao = new CustomerDaoImpl(connection);

        Customer youngestCustomer = customerDao.findYoungestCustomer();

        if (youngestCustomer != null) {
            System.out.println("Самый молодой клиент:");
            System.out.println("ID: " + youngestCustomer.getId());
            System.out.println("ФИО: " + youngestCustomer.getFullName());
            System.out.println("Дата рождения: " + youngestCustomer.getDateOfBirth());
            System.out.println("Телефон: " + youngestCustomer.getPhone());
            System.out.println("Email: " + youngestCustomer.getEmail());
            System.out.println("Скидка: " + youngestCustomer.getDiscount());
        } else {
            System.out.println("Самый молодой клиент не найден.");
        }
    }

    private static void oldestCustomer(Connection connection, Scanner scanner) throws SQLException {
        CustomerDaoImpl customerDao = new CustomerDaoImpl(connection);
        Customer oldestCustomer = customerDao.findOldestCustomer();

        if (oldestCustomer != null) {
            System.out.println("Самый возрастной клиент:");
            System.out.println("ID: " + oldestCustomer.getId());
            System.out.println("ФИО: " + oldestCustomer.getFullName());
            System.out.println("Дата рождения: " + oldestCustomer.getDateOfBirth());
            System.out.println("Телефон: " + oldestCustomer.getPhone());
            System.out.println("Email: " + oldestCustomer.getEmail());
            System.out.println("Скидка: " + oldestCustomer.getDiscount());
        } else {
            System.out.println("В базе данных нет информации о клиентах.");
        }
    }

    private static void customersWithBirthdayToday(Connection connection, Scanner scanner) throws SQLException {
        CustomerDaoImpl customerDao = new CustomerDaoImpl(connection);
        List<Customer> customers = customerDao.findCustomersByBirthday();
        if (customers.isEmpty()) {
            System.out.println("На сегодня у клиентов нет дней рождения.");
        } else {
            System.out.println("Клиенты с днем рождения сегодня:");
            for (Customer customer : customers) {
                System.out.println(customer.getFullName()+ "   " + customer.getDateOfBirth());
            }
        }
    }
    public static void CustomersWithNoEmail(Connection connection, Scanner scanner) throws SQLException {
        CustomerDaoImpl customerDao = new CustomerDaoImpl(connection);
        List<Customer> customersWithNoEmail = customerDao.findCustomersWithNoEmail();
        if (customersWithNoEmail.isEmpty()) {
            System.out.println("В базе данных нет клиентов без электронной почты.");
        } else {
            System.out.println("Список клиентов без электронной почты:");
            for (Customer customer : customersWithNoEmail) {
                System.out.println(customer.getFullName());
            }
        }
    }

    private static void showOrdersByDate(Connection connection, Scanner scanner) {
        OrderDaoImpl orderDao = new OrderDaoImpl(connection);
        scanner.nextLine();
        System.out.println("Введите дату в формате ГГГГ-ММ-ДД:");
        String dateString = scanner.nextLine();
        try {
            Date date = Date.valueOf(dateString);
            List<Order> orders = orderDao.findOrdersByDate(date);
            if (!orders.isEmpty()) {
                System.out.println("Информация о заказах в дату " + dateString + ":");
                for (Order order : orders) {
                    System.out.println(order);
                }
            } else {
                System.out.println("Нет заказов на указанную дату.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Некорректный формат даты.");
        }
    }

    private static void showOrdersBetweenDates(Connection connection, Scanner scanner) {
        scanner.nextLine();
        System.out.println("Введите начальную дату (гггг-мм-дд): ");
        String startDateStr = scanner.nextLine();
        System.out.println("Введите конечную дату (гггг-мм-дд): ");
        String endDateStr = scanner.nextLine();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate = new Date(dateFormat.parse(startDateStr).getTime());
            Date endDate = new Date(dateFormat.parse(endDateStr).getTime());

            OrderDaoImpl orderDao = new OrderDaoImpl(connection);
            List<Order> orders = orderDao.findOrdersBetweenDates(startDate, endDate);

            if (orders.isEmpty()) {
                System.out.println("Заказов в указанном промежутке дат не найдено.");
            } else {
                System.out.println("Информация о заказах в указанном промежутке дат:");
                for (Order order : orders) {
                    System.out.println(order);
                }
            }
        } catch (ParseException e) {
            System.out.println("Ошибка при разборе даты. Убедитесь, что вы ввели дату в формате гггг-мм-дд.");
        }
    }

    private static void showDessertOrdersCountByDate(Connection connection, Scanner scanner) {
        scanner.nextLine();
        System.out.println("Введите дату (гггг-мм-дд): ");
        String dateStr = scanner.nextLine();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = new Date(dateFormat.parse(dateStr).getTime());

            OrderItemDaoImpl orderItemDao = new OrderItemDaoImpl(connection);
            int dessertOrdersCount = orderItemDao.countDessertOrdersByDate(date);

            System.out.println("Количество заказанных десертов на " + dateStr + ": " + dessertOrdersCount);
        } catch (ParseException e) {
            System.out.println("Ошибка при разборе даты. Убедитесь, что вы ввели дату в формате гггг-мм-дд.");
        }
    }

    private static void showBeverageOrdersCountByDate(Connection connection, Scanner scanner) {
        scanner.nextLine();
        System.out.println("Введите дату (гггг-мм-дд): ");
        String dateStr = scanner.nextLine();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = new Date(dateFormat.parse(dateStr).getTime());

            OrderItemDaoImpl orderItemDao = new OrderItemDaoImpl(connection);
            int beverageOrdersCount = orderItemDao.countBeverageOrdersByDate(date);

            System.out.println("Количество заказанных напитков на " + dateStr + ": " + beverageOrdersCount);
        } catch (ParseException e) {
            System.out.println("Ошибка при разборе даты. Убедитесь, что вы ввели дату в формате гггг-мм-дд.");
        }
    }

    private static void showCustomersAndBaristasForBeverageOrdersToday(Connection connection, Scanner scanner) {
        CustomerDaoImpl customerDao = new CustomerDaoImpl(connection);
        List<String> customerAndBaristaInfos = customerDao.findCustomersAndBaristasForBeverageOrdersToday();
        if (customerAndBaristaInfos.isEmpty()) {
            System.out.println("На сегодня нет заказов напитков.");
        } else {
            System.out.println("Информация о клиентах и баристах для заказов напитков сегодня:");
            for (String info : customerAndBaristaInfos) {
                System.out.println(info);
            }
        }
    }

    public static void averageOrderAmountByDate(Connection connection, Scanner scanner) {
        scanner.nextLine();
        System.out.println("Введите дату (гггг-мм-дд): ");
        String dateStr = scanner.nextLine();

        try {
            Date date = Date.valueOf(dateStr);
            OrderDaoImpl orderDao = new OrderDaoImpl(connection);
            double averageAmount = orderDao.averageOrderAmountByDate(date);

            System.out.println("Средняя сумма заказа на дату " + dateStr + " составляет " + averageAmount);
        } catch (IllegalArgumentException e) {
            System.out.println("Неверный формат даты. Введите дату в формате гггг-мм-дд.");
        }
    }


    public static void maxOrderAmountByDate(Connection connection, Scanner scanner) {
        scanner.nextLine();
        System.out.println("Введите дату (гггг-мм-дд): ");
        String dateStr = scanner.nextLine();

        try {
            Date date = Date.valueOf(dateStr);
            OrderDaoImpl orderDao = new OrderDaoImpl(connection);
            double maxAmount = orderDao.maxOrderAmountByDate(date);

            if (maxAmount == -1) {
                System.out.println("На указанную дату " + dateStr + " нет заказов.");
            } else {
                System.out.println("Максимальная сумма заказа на дату " + dateStr + " составляет " + maxAmount);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Неверный формат даты. Введите дату в формате гггг-мм-дд.");
        }
    }

    public static void customerWithMaxOrderAmountByDate(Connection connection, Scanner scanner) {
        scanner.nextLine();
        System.out.println("Введите дату в формате ГГГГ-ММ-ДД:");
        String dateString = scanner.nextLine();
        Date date = Date.valueOf(dateString);

        CustomerDaoImpl customerDao = new CustomerDaoImpl(connection);
        List<String> customerData = customerDao.customerWithMaxOrderAmountByDate(date);

        if (customerData.isEmpty()) {
            System.out.println("На указанную дату нет заказов.");
        } else {
            System.out.println("Информация о клиенте с максимальной суммой заказа на указанную дату:");
            for (String data : customerData) {
                System.out.println(data);
            }
        }
    }

    public static void showBaristaScheduleForWeek(Connection connection, Scanner scanner) {
        scanner.nextLine();
        System.out.println("Введите имя бариста:");
        String fullName = scanner.nextLine();

        StaffDaoImpl staffDao = new StaffDaoImpl(connection);
        List<Staff> staffList = staffDao.findStaff(fullName);

        if (staffList.isEmpty()) {
            System.out.println("Бариста с таким именем не найден.");
        } else if (staffList.size() > 1) {
            System.out.println("Найдено несколько барист с таким именем. Пожалуйста, уточните запрос.");
        } else {
            int baristaId = staffList.get(0).getId();
            ScheduleDaoImpl scheduleDao = new ScheduleDaoImpl(connection);
            List<Schedule> scheduleForWeek = scheduleDao.findScheduleForBaristaByWeek(baristaId);

            if (scheduleForWeek.isEmpty()) {
                System.out.println("Расписание для данного баристы не найдено.");
            } else {
                System.out.println("Расписание работы бариста на неделю:");
                for (Schedule schedule : scheduleForWeek) {
                    System.out.println("День: " + schedule.getDayOfWeek() +
                            ", начало работы: " + schedule.getStartTime() +
                            ", конец работы: " + schedule.getEndTime());
                }
            }
        }
    }


    public static void showAllBaristasScheduleForWeek(Connection connection, Scanner scanner) {
        ScheduleDaoImpl scheduleDao = new ScheduleDaoImpl(connection);
        List<Schedule> schedules = scheduleDao.findScheduleForWeek();

        if (schedules.isEmpty()) {
            System.out.println("Расписание для баристов не найдено.");
        } else {
            System.out.println("Расписание работы всех баристов на неделю:");
            for (Schedule schedule : schedules) {
                System.out.println("ID: " + schedule.getId() +
                        ", ID бариста: " + schedule.getStaffId() +
                        ", День: " + schedule.getDayOfWeek() +
                        ", начало работы: " + schedule.getStartTime() +
                        ", конец работы: " + schedule.getEndTime());
            }
        }
    }


    public static void showScheduleForAllStaffByWeek(Connection connection) {
        ScheduleDaoImpl scheduleDao = new ScheduleDaoImpl(connection);
        List<Schedule> schedules = scheduleDao.findAll();

        if (schedules.isEmpty()) {
            System.out.println("Расписание работы для всех работников кафе на неделю не найдено.");
        } else {
            System.out.println("Расписание работы для всех работников кафе на неделю:");
            for (Schedule schedule : schedules) {
                System.out.println(schedule);
            }
        }
    }

}
