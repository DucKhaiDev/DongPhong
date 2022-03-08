package Services;

import Entity.WLItem;

import java.util.List;

public interface WLItemService {
    void insert(WLItem item);
    void edit(WLItem item);
    void delete(int wlItemId);
    WLItem getWLItem(int wlItemId);
    List<WLItem> getAll();
}
