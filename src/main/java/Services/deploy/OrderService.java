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
    public void delete(String ORD_ID) {
        orderDao.delete(ORD_ID);
    }

    @Override
    public Order getOrder(String ORD_ID) {
        return orderDao.getOrder(ORD_ID);
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }
}
