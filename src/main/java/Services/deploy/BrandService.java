package Services.deploy;

import Dao.deploy.BrandDao;
import Entity.Brand;
import Entity.Category;

import java.util.List;

public class BrandService implements Services.BrandService {
    BrandDao brandDao = new BrandDao();

    @Override
    public void insert(Brand brand) {
        brandDao.insert(brand);
    }

    @Override
    public void edit(Brand brand) {
        brandDao.edit(brand);
    }

    @Override
    public void delete(String BRA_ID) {
        brandDao.delete(BRA_ID);
    }

    @Override
    public Brand getBrand(String BRA_ID) {
        return brandDao.getBrand(BRA_ID);
    }

    @Override
    public List<Brand> getAll() {
        return brandDao.getAll();
    }

    @Override
    public List<Brand> searchByName(String NAME) {
        return brandDao.searchByName(NAME);
    }

    @Override
    public boolean checkExistID(String ID) {
        return brandDao.checkExistID(ID);
    }

    @Override
    public boolean isUnusedBrand(String BRA_ID) {
        return brandDao.isUnusedBrand(BRA_ID);
    }
}
