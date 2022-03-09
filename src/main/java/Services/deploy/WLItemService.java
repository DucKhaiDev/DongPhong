package Services.deploy;

import Dao.deploy.WLItemDao;
import Entity.WLItem;

import java.util.List;

public class WLItemService implements Services.WLItemService {
    private final WLItemDao itemDao = new WLItemDao();

    @Override
    public void insert(WLItem item) {
        itemDao.insert(item);
    }

    @Override
    public void edit(WLItem item) {
        itemDao.edit(item);
    }

    @Override
    public void delete(int wlItemId) {
        itemDao.delete(wlItemId);
    }

    @Override
    public WLItem getWLItem(int wlItemId) {
        return itemDao.getWLItem(wlItemId);
    }

    @Override
    public List<WLItem> getAll() {
        return itemDao.getAll();
    }

    @Override
    public List<WLItem> getItemByWishList(String wishListId) {
        return itemDao.getItemByWishList(wishListId);
    }

    @Override
    public boolean checkExistItem(String productId, String wishListId) {
        return itemDao.checkExistItem(productId, wishListId);
    }
}
