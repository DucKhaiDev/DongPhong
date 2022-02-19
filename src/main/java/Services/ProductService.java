package Services;

import Entity.Product;

import java.util.List;

public interface ProductService {
    void insert(Product product);
    void edit(Product newPro);
    void delete(String PRO_ID);
    Product getProduct(String PRO_ID);
    List<Product> getAll();
    List<Product> searchByName(String NAME);
    List<Product> searchByCategory(String CAT);
    List<Product> searchByBrand(String BRA);
    boolean checkExistID(String ID);
}
