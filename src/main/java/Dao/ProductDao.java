package Dao;

import Entity.Product;

import java.util.List;

public interface ProductDao {
    void insert(Product product);
    void edit(Product product);
    void delete(String PRO_ID);
    Product getProduct(String PRO_ID);
    List<Product> getAll();
    List<Product> searchByName(String NAME);
    List<Product> searchByNameInCategory(String CAT_ID, String NAME);
    List<Product> getProductByCategory(String CAT_ID);
    List<Product> getProductByBrand(String BRA_ID);
    boolean checkExistID(String ID);
    int countProduct(String CAT_ID, String BRA_ID);
}
