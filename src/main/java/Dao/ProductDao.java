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
    List<Product> searchByCategory(String CAT);
    List<Product> searchByBrand(String BRA);
    boolean checkExistPRO_ID(String PRO_ID);
}
