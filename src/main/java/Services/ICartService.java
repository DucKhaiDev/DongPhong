package Services;

import Entity.Cart;

import java.util.List;

public interface ICartService {
    void insert(Cart cart);

    void edit(Cart cart);

    void delete(String cartId);

    Cart getCart(String cartId);

    List<Cart> getCartByUser(String userId);

    List<Cart> getAll();

    Cart getLastCart(String userId);
}
