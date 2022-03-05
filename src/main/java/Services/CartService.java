package Services;

import Entity.Cart;

import java.util.List;

public interface CartService {
    void insert(Cart cart);
    void edit(Cart cart);
    void delete(String CART_ID);
    Cart getCart(String CART_ID);
    List<Cart> getAll();
}
