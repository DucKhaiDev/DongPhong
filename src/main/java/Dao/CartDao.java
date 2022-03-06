package Dao;

import Entity.Cart;

import java.util.List;

public interface CartDao {
    void insert(Cart cart);
    void edit(Cart cart);
    void delete(String cartId);
    Cart getCart(String cartId);
    List<Cart> getAll();
}
