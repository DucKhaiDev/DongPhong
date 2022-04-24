package Dao;

import Entity.Order;

import java.util.List;

public interface IOrderDao {
    void insert(Order order);
    void edit(Order order);
    void delete(int orderId);
    Order getOrder(int orderId);
    Order getNewestOrder();
    List<Order> getAll();
}