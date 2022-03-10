package Dao;

import Entity.CartItem;

import java.util.List;

public interface CartItemDao {
    void insert(CartItem item);
    void edit(CartItem item);
    void delete(int cartItemId);
    CartItem getCartItem(int cartItemId);
    CartItem getCartItem(String productId, String cartId);
    List<CartItem> getAll();
    List<CartItem> getItemByCart(String cartId);
    boolean checkExistItem(String productId, String cartId);
}
