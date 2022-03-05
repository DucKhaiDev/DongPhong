package Services;

import Entity.WLItem;

import java.util.List;

public interface WLItemService {
    void insert(WLItem item);
    void edit(WLItem item);
    void delete(String WLITEM_ID);
    WLItem getWLItem(String WLITEM_ID);
    List<WLItem> getAll();
}
