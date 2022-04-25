package Dao;

import Entity.ProImage;

import java.util.List;

public interface IProImageDao {
    void insert(ProImage image);

    void edit(ProImage image);

    void delete(int imageId);

    void delete(String productId);

    ProImage getImage(int imageId);

    List<ProImage> getProImage(String productId);

    List<ProImage> getAll();
}
