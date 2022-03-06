package Services;

import Entity.WLItem;

import java.util.List;

public interface WLItemService {
    void insert(WLItem item);
    void edit(WLItem item);
    void delete(String wlItemId);
    WLItem getWLItem(String wlItemId);
    List<WLItem> getAll();
}
