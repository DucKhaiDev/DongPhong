package Services.deploy;

import Dao.deploy.ProImageDao;
import Entity.ProImage;

import java.util.List;

public class ProImageService implements Services.ProImageService {
    private final ProImageDao imageDao = new ProImageDao();

    @Override
    public void insert(ProImage image) {
        imageDao.insert(image);
    }

    @Override
    public void edit(ProImage image) {
        imageDao.edit(image);
    }

    @Override
    public void delete(int imageId) {
        imageDao.delete(imageId);
    }

    @Override
    public void delete(String productId) {
        imageDao.delete(productId);
    }

    @Override
    public ProImage getImage(int imageId) {
        return imageDao.getImage(imageId);
    }

    @Override
    public String getProReImage(String productId) {
        List<ProImage> images = getProImage(productId);
        if (!images.isEmpty()) {
            return images.get(0).getImageName();
        }

        return null;
    }

    @Override
    public List<ProImage> getProImage(String productId) {
        return imageDao.getProImage(productId);
    }

    @Override
    public List<ProImage> getAll() {
        return imageDao.getAll();
    }
}
