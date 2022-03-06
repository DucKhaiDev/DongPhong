package Services.deploy;

import Dao.deploy.OrderDao;
import Entity.Order;

import java.util.List;

public class OrderService implements Services.OrderService {
    private final OrderDao orderDao = new OrderDao();

    @Override
    public void insert(Order order) {
        orderDao.insert(order);
    }

    @Override
    public void edit(Order order) {
        orderDao.edit(order);
    }

    @Override
    public void delete(String orderId) {
        orderDao.delete(orderId);
    }

    @Override
    public Order getOrder(String orderId) {
        return orderDao.getOrder(orderId);
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }
}
