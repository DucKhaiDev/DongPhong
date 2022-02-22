package Services.deploy;

import Dao.deploy.ProImageDao;
import Entity.ProImage;

import java.util.List;

public class ProImageService implements Services.ProImageService {
    ProImageDao imageDao = new ProImageDao();

    @Override
    public void insert(ProImage image) {
        imageDao.insert(image);
    }

    @Override
    public void edit(ProImage image) {
        imageDao.edit(image);
    }

    @Override
    public void delete(int IMG_ID) {
        imageDao.delete(IMG_ID);
    }

    @Override
    public void delete(String PRO_ID) {
        imageDao.delete(PRO_ID);
    }

    @Override
    public ProImage getImage(int IMG_ID) {
        return imageDao.getImage(IMG_ID);
    }

    @Override
    public List<ProImage> getProImage(String PRO_ID) {
        return imageDao.getProImage(PRO_ID);
    }

    @Override
    public List<ProImage> getAll() {
        return imageDao.getAll();
    }
}
