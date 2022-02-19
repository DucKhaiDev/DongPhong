package Dao;

import Entity.Brand;
import Entity.Category;

import java.util.List;

public interface BrandDao {
    void insert(Brand brand);
    void edit(Brand brand);
    void delete(String BRA_ID);
    Brand getBrand(String BRA_ID);
    List<Brand> getAll();
    List<Brand> searchByName(String NAME);
    boolean checkExistID(String ID);
}
