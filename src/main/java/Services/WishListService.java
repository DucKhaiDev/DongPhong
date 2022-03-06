package Services;

import Entity.WishList;

import java.util.List;

public interface WishListService {
    void insert(WishList wishList);
    void edit(WishList wishList);
    void delete(String wishListId);
    WishList getWishlist(String wishListId);
    List<WishList> getAll();
}
