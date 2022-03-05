package Services;

import Entity.CartItem;

import java.util.List;

public interface CartItemService {
    void insert(CartItem item);
    void edit(CartItem item);
    void delete(String CITEM_ID);
    CartItem getCartItem(String CITEM_ID);
    List<CartItem> getAll();
}
