package Services.deploy;

import Dao.deploy.CartItemDao;
import Entity.CartItem;

import java.util.List;

public class CartItemService implements Services.CartItemService {
    private final CartItemDao itemDao = new CartItemDao();

    @Override
    public void insert(CartItem item) {
        itemDao.insert(item);
    }

    @Override
    public void edit(CartItem item) {
        itemDao.edit(item);
    }

    @Override
    public void delete(String CITEM_ID) {
        itemDao.delete(CITEM_ID);
    }

    @Override
    public CartItem getCartItem(String CITEM_ID) {
        return itemDao.getCartItem(CITEM_ID);
    }

    @Override
    public List<CartItem> getAll() {
        return itemDao.getAll();
    }
}
