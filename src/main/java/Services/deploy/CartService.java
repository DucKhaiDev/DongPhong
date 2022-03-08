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
    public void delete(String cartId) {
        cartDao.delete(cartId);
    }

    @Override
    public Cart getCart(String cartId) {
        return cartDao.getCart(cartId);
    }

    @Override
    public Cart getCartByUser(String userId) {
        return cartDao.getCartByUser(userId);
    }

    @Override
    public List<Cart> getAll() {
        return cartDao.getAll();
    }
}
