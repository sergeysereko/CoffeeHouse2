package org.example.dao.customerDAO;

import org.example.dao.CRUDInterface;
import org.example.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    private Connection connection;

    public CustomerDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Customer customer) {
        String query = "INSERT INTO customers (full_name, date_of_birth, phone, email, discount) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, customer.getFullName());
            preparedStatement.setDate(2, (Date) customer.getDateOfBirth());
            preparedStatement.setString(3, customer.getPhone());
            preparedStatement.setString(4, customer.getEmail());
            preparedStatement.setDouble(5, customer.getDiscount());
            preparedStatement.executeUpdate();
            System.out.println("Новый клиент успешно добавлен!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveMany(List<Customer> customers) {
        for (Customer customer : customers) {
            save(customer);
        }
    }

    @Override
    public void update(Customer customer) {
        String query = "UPDATE customers SET date_of_birth = ?, phone = ?, email = ?, discount = ? WHERE full_name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDate(1, (Date) customer.getDateOfBirth());
            preparedStatement.setString(2, customer.getPhone());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setDouble(4, customer.getDiscount());
            preparedStatement.setString(5, customer.getFullName());
            preparedStatement.executeUpdate();
            System.out.println("Информация о клиенте успешно обновлена!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Customer customer) {
        String query = "DELETE FROM customers WHERE full_name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, customer.getFullName());
            preparedStatement.executeUpdate();
            System.out.println("Информация о клиенте успешно удалена!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customers";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setFullName(resultSet.getString("full_name"));
                customer.setDateOfBirth(resultSet.getDate("date_of_birth"));
                customer.setPhone(resultSet.getString("phone"));
                customer.setEmail(resultSet.getString("email"));
                customer.setDiscount(resultSet.getDouble("discount"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public void deleteAll() {
        String query = "DELETE FROM customers";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
            System.out.println("Вся информация о клиентах успешно удалена!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> findCustomer(String fullName) {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customers WHERE full_name LIKE ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "%" + fullName + "%");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Customer customer = new Customer();
                    customer.setId(resultSet.getInt("id"));
                    customer.setFullName(resultSet.getString("full_name"));
                    customer.setDateOfBirth(resultSet.getDate("date_of_birth"));
                    customer.setPhone(resultSet.getString("phone"));
                    customer.setEmail(resultSet.getString("email"));
                    customer.setDiscount(resultSet.getDouble("discount"));
                    customers.add(customer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public double findMinimumDiscount() {
        String query = "SELECT MIN(discount) AS min_discount FROM customers";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getDouble("min_discount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Double.MAX_VALUE; // Если нет клиентов, возвращаем максимальное значение
    }

    @Override
    public List<Customer> findCustomersByDiscount(double discount) {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customers WHERE discount = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, discount);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Customer customer = new Customer();
                    customer.setId(resultSet.getInt("id"));
                    customer.setFullName(resultSet.getString("full_name"));
                    customer.setDateOfBirth(resultSet.getDate("date_of_birth"));
                    customer.setPhone(resultSet.getString("phone"));
                    customer.setEmail(resultSet.getString("email"));
                    customer.setDiscount(resultSet.getDouble("discount"));
                    customers.add(customer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public double findMaximumDiscount() {
        double maxDiscount = 0.0;
        String query = "SELECT MAX(discount) AS max_discount FROM customers";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                maxDiscount = resultSet.getDouble("max_discount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxDiscount;
    }

    @Override
    public List<Customer> findCustomersWithMinimumDiscount() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customers WHERE discount = (SELECT MIN(discount) FROM customers)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setFullName(resultSet.getString("full_name"));
                customer.setDateOfBirth(resultSet.getDate("date_of_birth"));
                customer.setPhone(resultSet.getString("phone"));
                customer.setEmail(resultSet.getString("email"));
                customer.setDiscount(resultSet.getDouble("discount"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public List<Customer> findCustomersWithMaximumDiscount() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customers WHERE discount = (SELECT MAX(discount) FROM customers)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setFullName(resultSet.getString("full_name"));
                customer.setDateOfBirth(resultSet.getDate("date_of_birth"));
                customer.setPhone(resultSet.getString("phone"));
                customer.setEmail(resultSet.getString("email"));
                customer.setDiscount(resultSet.getDouble("discount"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public double findAverageDiscount() {
        double averageDiscount = 0;
        String query = "SELECT AVG(discount) AS average_discount FROM customers";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                averageDiscount = resultSet.getDouble("average_discount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return averageDiscount;
    }

    @Override
    public Customer findYoungestCustomer() {
        Customer youngestCustomer = null;
        String query = "SELECT * FROM customers ORDER BY date_of_birth DESC LIMIT 1";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                youngestCustomer = new Customer();
                youngestCustomer.setId(resultSet.getInt("id"));
                youngestCustomer.setFullName(resultSet.getString("full_name"));
                youngestCustomer.setDateOfBirth(resultSet.getDate("date_of_birth"));
                youngestCustomer.setPhone(resultSet.getString("phone"));
                youngestCustomer.setEmail(resultSet.getString("email"));
                youngestCustomer.setDiscount(resultSet.getDouble("discount"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return youngestCustomer;
    }
    @Override
    public Customer findOldestCustomer() {
        Customer oldestCustomer = null;
        String query = "SELECT * FROM customers ORDER BY date_of_birth ASC LIMIT 1";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                oldestCustomer = new Customer();
                oldestCustomer.setId(resultSet.getInt("id"));
                oldestCustomer.setFullName(resultSet.getString("full_name"));
                oldestCustomer.setDateOfBirth(resultSet.getDate("date_of_birth"));
                oldestCustomer.setPhone(resultSet.getString("phone"));
                oldestCustomer.setEmail(resultSet.getString("email"));
                oldestCustomer.setDiscount(resultSet.getDouble("discount"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oldestCustomer;
    }

    @Override
    public List<Customer> findCustomersByBirthday() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customers WHERE TO_CHAR(date_of_birth, 'MM-DD') = TO_CHAR(CURRENT_DATE, 'MM-DD')";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setFullName(resultSet.getString("full_name"));
                customer.setDateOfBirth(resultSet.getDate("date_of_birth"));
                customer.setPhone(resultSet.getString("phone"));
                customer.setEmail(resultSet.getString("email"));
                customer.setDiscount(resultSet.getDouble("discount"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public List<Customer> findCustomersWithNoEmail() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customers WHERE email IS NULL OR email = ''";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setFullName(resultSet.getString("full_name"));
                customer.setDateOfBirth(resultSet.getDate("date_of_birth"));
                customer.setPhone(resultSet.getString("phone"));
                customer.setEmail(resultSet.getString("email"));
                customer.setDiscount(resultSet.getDouble("discount"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }


    @Override
    public List<String> findCustomersAndBaristasForBeverageOrdersToday() {
        List<String> customerAndBaristaInfos = new ArrayList<>();
        String query = "SELECT c.full_name AS customer_name, s.full_name AS barista_name " +
                "FROM orderitems oi " +
                "JOIN orders o ON oi.order_id = o.id " +
                "JOIN customers c ON o.customer_id = c.id " +
                "JOIN staff s ON oi.barista_id = s.id " +
                "WHERE oi.item_type = 'drink' AND DATE(o.order_date) = CURRENT_DATE";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String customerName = resultSet.getString("customer_name");
                String baristaName = resultSet.getString("barista_name");
                customerAndBaristaInfos.add("Клиент: " + customerName + ", Бариста: " + baristaName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerAndBaristaInfos;
    }

    @Override
    public List<String> customerWithMaxOrderAmountByDate(Date date) {
        List<String> customerData = new ArrayList<>();
        String query = "SELECT customers.*, orders.total_amount " +
                "FROM orders " +
                "JOIN customers ON orders.customer_id = customers.id " +
                "WHERE orders.order_date = ? " +
                "ORDER BY orders.total_amount DESC " +
                "LIMIT 1";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDate(1, date);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    StringBuilder customerInfo = new StringBuilder();
                    customerInfo.append("Customer ID: ").append(resultSet.getInt("id")).append("\n");
                    customerInfo.append("Full Name: ").append(resultSet.getString("full_name")).append("\n");
                    customerInfo.append("Date of Birth: ").append(resultSet.getDate("date_of_birth")).append("\n");
                    customerInfo.append("Phone: ").append(resultSet.getString("phone")).append("\n");
                    customerInfo.append("Email: ").append(resultSet.getString("email")).append("\n");
                    customerInfo.append("Discount: ").append(resultSet.getDouble("discount")).append("\n");

                    double orderAmount = resultSet.getDouble("total_amount");

                    customerInfo.append("Order Amount: ").append(orderAmount).append("\n");

                    customerData.add(customerInfo.toString());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerData;
    }

}