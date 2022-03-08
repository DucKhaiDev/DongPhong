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
    public void delete(int cartItemId) {
        itemDao.delete(cartItemId);
    }

    @Override
    public CartItem getCartItem(int cartItemId) {
        return itemDao.getCartItem(cartItemId);
    }

    @Override
    public List<CartItem> getAll() {
        return itemDao.getAll();
    }
}
