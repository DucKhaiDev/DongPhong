package Services.deploy;

import Dao.deploy.TempLinkDao;
import Entity.TempLink;
import Entity.User;
import Services.ITempLinkService;

public class TempLinkService implements ITempLinkService {
    private final TempLinkDao tempLinkDao = new TempLinkDao();

    @Override
    public void insert(TempLink tempLink) {
        tempLinkDao.insert(tempLink);
    }

    @Override
    public void delete(String token) {
        tempLinkDao.delete(token);
    }

    @Override
    public void delete(User user) {
        tempLinkDao.delete(user);
    }

    @Override
    public TempLink getTempLink(String token) {
        return tempLinkDao.getTempLink(token);
    }
}
