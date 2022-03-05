package Dao;

import Entity.Wishlist;

import java.util.List;

public interface WishlistDao {
    void insert(Wishlist wishlist);
    void edit(Wishlist wishlist);
    void delete(String WL_ID);
    Wishlist getWishlist(String WL_ID);
    List<Wishlist> getAll();
}
