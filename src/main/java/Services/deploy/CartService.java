package Services.deploy;

import Dao.deploy.CartDao;
import Entity.Cart;
import Services.ICartService;

import java.util.List;

public class CartService implements ICartService {
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
    public void delete(String cartId) {
        cartDao.delete(cartId);
    }

    @Override
    public Cart getCart(String cartId) {
        return cartDao.getCart(cartId);
    }

    @Override
    public List<Cart> getCartByUser(String userId) {
        return cartDao.getCartByUser(userId);
    }

    @Override
    public List<Cart> getAll() {
        return cartDao.getAll();
    }

    @Override
    public Cart getLastCart(String userId) {
        return cartDao.getLastCart(userId);
    }
}
