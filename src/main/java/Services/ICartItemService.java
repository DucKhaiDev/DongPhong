package Services;

import Entity.CartItem;

import java.math.BigDecimal;
import java.util.List;

public interface ICartItemService {
    void insert(CartItem item);

    void edit(CartItem item);

    void delete(int cartItemId);

    void deleteAll(String cartId);

    CartItem getCartItem(int cartItemId);

    CartItem getCartItem(String productId, String cartId);

    List<CartItem> getAll();

    List<CartItem> getItemByCart(String cartId);

    boolean checkExistItem(String productId, String cartId);

    int countSumQuantity(String cartId);

    BigDecimal getSubTotal(String cartId);
}