package org.example.dao.customerDAO;

import org.example.dao.CRUDInterface;
import org.example.model.Customer;

import java.sql.Date;
import java.util.List;

public interface CustomerDao extends CRUDInterface<Customer> {

    List<Customer> findCustomer(String fullName);
    public double findMinimumDiscount();
    public List<Customer> findCustomersByDiscount(double discount);
    public double findMaximumDiscount();
    public List<Customer> findCustomersWithMinimumDiscount();
    public List<Customer> findCustomersWithMaximumDiscount();
    public double findAverageDiscount();
    public Customer findYoungestCustomer();
    public Customer findOldestCustomer();
    public List<Customer> findCustomersByBirthday();
    public List<Customer> findCustomersWithNoEmail();
    public List<String> findCustomersAndBaristasForBeverageOrdersToday();
    public List<String> customerWithMaxOrderAmountByDate(Date date);
}
