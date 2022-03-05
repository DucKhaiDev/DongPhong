package Services.deploy;

import Dao.deploy.CartDao;
import Entity.Cart;

import java.util.List;

public class CartService implements Services.CartService {
    private final CartDao cartDao = new CartDao();

    @Override
    public void insert(Cart cart) {
        cartDao.insert(cart);
    }

    @Override
    public void edit(Cart cart) {
        cartDao.edit(cart);
    }

    @Override
    public void delete(String CART_ID) {
        cartDao.delete(CART_ID);
    }

    @Override
    public Cart getCart(String CART_ID) {
        return cartDao.getCart(CART_ID);
    }

    @Override
    public List<Cart> getAll() {
        return cartDao.getAll();
    }
}
