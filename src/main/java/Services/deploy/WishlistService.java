package Services.deploy;

import Dao.deploy.WishlistDao;
import Entity.Wishlist;

import java.util.List;

public class WishlistService implements Services.WishlistService {
    WishlistDao wishlistDao = new WishlistDao();

    @Override
    public void insert(Wishlist wishlist) {
        wishlistDao.insert(wishlist);
    }

    @Override
    public void edit(Wishlist wishlist) {
        wishlistDao.edit(wishlist);
    }

    @Override
    public void delete(String WL_ID) {
        wishlistDao.delete(WL_ID);
    }

    @Override
    public Wishlist getWishlist(String WL_ID) {
        return wishlistDao.getWishlist(WL_ID);
    }

    @Override
    public List<Wishlist> getAll() {
        return wishlistDao.getAll();
    }
}
