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
    public List<Product> searchByCategory(String CAT) {
        return productDao.searchByCategory(CAT);
    }

    @Override
    public List<Product> searchByBrand(String BRA) {
        return productDao.searchByBrand(BRA);
    }

    @Override
    public boolean checkExistID(String PRO_ID) {
        return productDao.checkExistID(PRO_ID);
    }
}
