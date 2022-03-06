package Dao;

import Entity.Order;

import java.util.List;

public interface OrderDao {
    void insert(Order order);
    void edit(Order order);
    void delete(String orderId);
    Order getOrder(String orderId);
    List<Order> getAll();
}
