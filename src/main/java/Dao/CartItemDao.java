package Dao;

import Entity.CartItem;

import java.util.List;

public interface CartItemDao {
    void insert(CartItem item);
    void edit(CartItem item);
    void delete(String cartItemId);
    CartItem getCartItem(String cartItemId);
    List<CartItem> getAll();
}
