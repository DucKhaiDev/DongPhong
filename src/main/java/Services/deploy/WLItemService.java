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
    public void delete(String WLITEM_ID) {
        itemDao.delete(WLITEM_ID);
    }

    @Override
    public WLItem getWLItem(String WLITEM_ID) {
        return itemDao.getWLItem(WLITEM_ID);
    }

    @Override
    public List<WLItem> getAll() {
        return itemDao.getAll();
    }
}
