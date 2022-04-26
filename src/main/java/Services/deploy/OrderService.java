package Services.deploy;

import Dao.deploy.OrderDao;
import Entity.Order;
import Services.IOrderService;

import java.util.List;

public class OrderService implements IOrderService {
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
    public void delete(int orderId) {
        orderDao.delete(orderId);
    }

    @Override
    public Order getOrder(int orderId) {
        return orderDao.getOrder(orderId);
    }

    @Override
    public Order getNewestOrder() {
        return orderDao.getNewestOrder();
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public int countPendingOrder() {
        return orderDao.countPendingOrder();
    }
}
