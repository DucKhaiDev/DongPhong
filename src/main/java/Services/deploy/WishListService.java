package Services.deploy;

import Dao.deploy.WishlistDao;
import Entity.WishList;

import java.util.List;

public class WishListService implements Services.WishListService {
    WishlistDao wishlistDao = new WishlistDao();

    @Override
    public void insert(WishList wishList) {
        wishlistDao.insert(wishList);
    }

    @Override
    public void edit(WishList wishList) {
        wishlistDao.edit(wishList);
    }

    @Override
    public void delete(String wishListId) {
        wishlistDao.delete(wishListId);
    }

    @Override
    public WishList getWishlist(String wishListId) {
        return wishlistDao.getWishlist(wishListId);
    }

    @Override
    public List<WishList> getAll() {
        return wishlistDao.getAll();
    }
}
