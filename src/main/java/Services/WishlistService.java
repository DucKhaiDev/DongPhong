package Services;

import Entity.Wishlist;

import java.util.List;

public interface WishlistService {
    void insert(Wishlist wishlist);
    void edit(Wishlist wishlist);
    void delete(String WL_ID);
    Wishlist getWishlist(String WL_ID);
    List<Wishlist> getAll();
}
