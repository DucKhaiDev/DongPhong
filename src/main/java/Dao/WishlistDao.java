package Dao;

import Entity.WishList;

import java.util.List;

public interface WishlistDao {
    void insert(WishList wishlist);
    void edit(WishList wishlist);
    void delete(String wishListId);
    WishList getWishlist(String wishListId);
    WishList getWishListByUser(String userId);
    List<WishList> getAll();
}
