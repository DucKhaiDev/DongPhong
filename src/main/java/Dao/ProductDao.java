package Dao;

import Entity.Product;

import java.util.List;

public interface ProductDao {
    void insert(Product product);
    void edit(Product product);
    void delete(String productId);
    Product getProduct(String productId);
    List<Product> getAll();
    List<Product> searchByName(String productName);
    List<Product> searchByNameInCategory(String categoryId, String name);
    List<Product> getProductByCategory(String categoryId);
    List<Product> getProductByBrand(String brandId);
    boolean checkExistId(String id);
    int countProduct(String categoryId, String brandId);
}
