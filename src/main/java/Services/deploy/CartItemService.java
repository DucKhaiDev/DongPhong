package Services.deploy;

import Dao.deploy.CartItemDao;
import Entity.CartItem;
import Services.ICartItemService;

import java.math.BigDecimal;
import java.util.List;

public class CartItemService implements ICartItemService {
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
    public void deleteAll(String cartId) {
        itemDao.deleteAll(cartId);
    }

    @Override
    public CartItem getCartItem(int cartItemId) {
        return itemDao.getCartItem(cartItemId);
    }

    @Override
    public CartItem getCartItem(String productId, String cartId) {
        return itemDao.getCartItem(productId, cartId);
    }

    @Override
    public List<CartItem> getAll() {
        return itemDao.getAll();
    }

    @Override
    public List<CartItem> getItemByCart(String cartId) {
        return itemDao.getItemByCart(cartId);
    }

    @Override
    public boolean checkExistItem(String productId, String cartId) {
        return itemDao.checkExistItem(productId, cartId);
    }

    @Override
    public int countSumQuantity(String cartId) {
        return itemDao.countSumQuantity(cartId);
    }

    @Override
    public BigDecimal getSubTotal(String cartId) {
        return itemDao.getSubTotal(cartId);
    }
}