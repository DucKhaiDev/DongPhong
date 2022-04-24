package Dao;

import Entity.WLItem;

import java.util.List;

public interface IWLItemDao {
    void insert(WLItem item);
    void edit(WLItem item);
    void delete(int wlItemId);
    WLItem getWLItem(int wlItemId);
    List<WLItem> getAll();
    List<WLItem> getItemByWishList(String wishListId);
    boolean checkExistItem(String productId, String wishListId);
}