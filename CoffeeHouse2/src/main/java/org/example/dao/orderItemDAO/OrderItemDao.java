package org.example.dao.orderItemDAO;

import org.example.dao.CRUDInterface;
import org.example.model.OrderItem;

import java.sql.Date;
import java.util.List;

public interface OrderItemDao extends CRUDInterface<OrderItem> {

    public List<OrderItem> findOrderItemsByDessertId(int dessertId);

    public List<OrderItem> findOrderItemsByOrderId(int orderId);

    public void deleteOrderItemsByOrderId(int orderId);

    public int countDessertOrdersByDate(Date date);
    public int countBeverageOrdersByDate(Date date);

}
