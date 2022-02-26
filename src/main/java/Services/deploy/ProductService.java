package Services.deploy;

import Dao.deploy.ProductDao;
import Entity.Product;

import java.util.List;

public class ProductService implements Services.ProductService {
    ProductDao productDao = new ProductDao();

    @Override
    public void insert(Product product) {
        productDao.insert(product);
    }

    @Override
    public void edit(Product product) {
       productDao.edit(product);
    }

    @Override
    public void delete(String PRO_ID) {
        productDao.delete(PRO_ID);
    }

    @Override
    public Product getProduct(String PRO_ID) {
        return productDao.getProduct(PRO_ID);
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public List<Product> searchByName(String NAME) {
        return productDao.searchByName(NAME);
    }

    @Override
    public List<Product> getProductByCategory(String CAT_ID) {
        return productDao.getProductByCategory(CAT_ID);
    }

    @Override
    public List<Product> getProductByBrand(String BRA_ID) {
        return productDao.getProductByBrand(BRA_ID);
    }

    @Override
    public boolean checkExistID(String PRO_ID) {
        return productDao.checkExistID(PRO_ID);
    }

    @Override
    public int countProduct(String CAT_ID, String BRA_ID) {
        return productDao.countProduct(CAT_ID, BRA_ID);
    }
}
