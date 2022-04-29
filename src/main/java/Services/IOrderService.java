package Services;

import Entity.Order;
import Entity.User;

import java.util.List;

public interface IOrderService {
    void insert(Order order);

    void edit(Order order);

    void delete(int orderId);

    Order getOrder(int orderId);

    Order getNewestOrder();

    List<Order> getAll();

    List<Order> getAll(User user);

    int countPendingOrder();
}