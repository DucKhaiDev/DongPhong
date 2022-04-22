package Services;

import Entity.WishList;

import java.util.List;

public interface IWishListService {
    void insert(WishList wishList);
    void edit(WishList wishList);
    void delete(String wishListId);
    WishList getWishlist(String wishListId);
    WishList getWishListByUser(String userId);
    List<WishList> getAll();
}
