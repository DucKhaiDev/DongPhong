package Services.deploy;

import Dao.deploy.BrandDao;
import Entity.Brand;
import Services.IBrandService;

import java.util.List;

public class BrandService implements IBrandService {
    private final BrandDao brandDao = new BrandDao();

    @Override
    public void insert(Brand brand) {
        brandDao.insert(brand);
    }

    @Override
    public void edit(Brand brand) {
        brandDao.edit(brand);
    }

    @Override
    public void delete(String brandId) {
        brandDao.delete(brandId);
    }

    @Override
    public Brand getBrand(String brandId) {
        return brandDao.getBrand(brandId);
    }

    @Override
    public List<Brand> getAll() {
        return brandDao.getAll();
    }

    @Override
    public List<Brand> searchByName(String brandName) {
        return brandDao.searchByName(brandName);
    }

    @Override
    public boolean checkExistId(String id) {
        return brandDao.checkExistId(id);
    }

    @Override
    public boolean isUnusedBrand(String brandId) {
        return brandDao.isUnusedBrand(brandId);
    }
}
