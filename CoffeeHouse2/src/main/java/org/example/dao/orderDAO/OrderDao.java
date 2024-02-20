package org.example.dao.orderDAO;

import org.example.dao.CRUDInterface;
import org.example.model.Customer;
import org.example.model.Order;

import java.sql.Date;
import java.util.List;

public interface OrderDao extends CRUDInterface<Order> {
    public List<Order> findOrdersByCustomerId(int customerId);
    public Order findById(int orderId);
    public int getLastOrderId();
    public List<Order> findAllOrdersByWaiterId(int waiterId);
    public List<Order> findOrdersByDate(Date date);

    public List<Order> findOrdersBetweenDates(Date startDate, Date endDate);
    public double averageOrderAmountByDate(Date date);
    public double maxOrderAmountByDate(Date date);


}
