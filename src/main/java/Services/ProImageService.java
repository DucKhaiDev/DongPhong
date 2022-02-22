package Services;

import Entity.ProImage;

import java.util.List;

public interface ProImageService {
    void insert(ProImage image);
    void edit(ProImage image);
    void delete(int IMG_ID);
    void delete(String PRO_ID);
    ProImage getImage(int IMG_ID);
    List<ProImage> getProImage(String PRO_ID);
    List<ProImage> getAll();
}
