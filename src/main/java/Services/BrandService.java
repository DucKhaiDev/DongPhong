package Services;

import Entity.Brand;
import Entity.Category;

import java.util.List;

public interface BrandService {
    void insert(Brand brand);
    void edit(Brand brand);
    void delete(String brandId);
    Brand getBrand(String brandId);
    List<Brand> getAll();
    List<Brand> searchByName(String brandName);
    boolean checkExistId(String id);
    boolean isUnusedBrand(String brandId);
}
