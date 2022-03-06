package Dao;

import Entity.WLItem;

import java.util.List;

public interface WLItemDao {
    void insert(WLItem item);
    void edit(WLItem item);
    void delete(String wlItemId);
    WLItem getWLItem(String wlItemId);
    List<WLItem> getAll();
}
