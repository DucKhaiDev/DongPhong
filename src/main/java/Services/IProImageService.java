package Services;

import Entity.ProImage;

import java.util.List;

public interface IProImageService {
    void insert(ProImage image);
    void edit(ProImage image);
    void delete(int imageId);
    void delete(String productId);
    ProImage getImage(int imageId);
    String getProReImage(String productId);
    List<ProImage> getProImage(String productId);
    List<ProImage> getAll();
}
