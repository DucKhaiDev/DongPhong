package Services;

import Entity.Order;

import java.util.List;

public interface OrderService {
    void insert(Order order);
    void edit(Order order);
    void delete(String ORD_ID);
    Order getOrder(String ORD_ID);
    List<Order> getAll();
}
