package Dao;

import Entity.Brand;

import java.util.List;

public interface IBrandDao {
    void insert(Brand brand);
    void edit(Brand brand);
    void delete(String brandId);
    Brand getBrand(String brandId);
    List<Brand> getAll();
    List<Brand> searchByName(String brandName);
    boolean checkExistId(String id);
    boolean isUnusedBrand(String brandId);
}
