package Dao;

import Entity.CartItem;

import java.util.List;

public interface CartItemDao {
    void insert(CartItem item);
    void edit(CartItem item);
    void delete(int cartItemId);
    CartItem getCartItem(int cartItemId);
    List<CartItem> getAll();
}
