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
    public String getProReImage(String PRO_ID) {
        List<ProImage> images = getProImage(PRO_ID);
        if (!images.isEmpty()) {
            return images.get(0).getIMG_NAME();
        }

        return null;
    }

    @Override
    public List<ProImage> getProImage(String PRO_ID) {
        return imageDao.getProImage(PRO_ID);
    }

    @Override
    public List<ProImage> getAll() {
        return imageDao.getAll();
    }

    public static void main(String[] args) {
        ProImageService imageService = new ProImageService();
        String reImage = imageService.getProReImage("SLG01");
        System.out.println(reImage);
    }
}
