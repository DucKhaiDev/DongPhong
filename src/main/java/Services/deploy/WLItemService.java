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
    public void delete(String wlItemId) {
        itemDao.delete(wlItemId);
    }

    @Override
    public WLItem getWLItem(String wlItemId) {
        return itemDao.getWLItem(wlItemId);
    }

    @Override
    public List<WLItem> getAll() {
        return itemDao.getAll();
    }
}
