package Services;

import Entity.Product;

import java.util.List;

public interface ProductService {
    void insert(Product product);
    void edit(Product product);
    void delete(String productId);
    Product getProduct(String productId);
    List<Product> getAll();
    List<Product> searchByName(String productName);
    List<Product> searchByNameInCategory(String categoryId, String productName);
    List<Product> getProductByCategory(String categoryId);
    List<Product> getProductByBrand(String brandId);
    boolean checkExistId(String id);
    int countProduct(String categoryId, String brandId);
    void sortByPriceAsc(List<Product> products);
    void sortByPriceDesc(List<Product> products);
    List<Product> filterProductByBrand(List<Product> products, String brands);
    List<Product> filterProductByPrice(List<Product> products, String price);
    List<Product> filterProductByStars(List<Product> products, String stars);
    int countProduct(String roomId);
    int countPrd_RoomBrand(String roomId, String brandId);
    List<Product> getProductByRoom(String roomId);
    List<Product> searchByNameInRoom(String roomId, String name);
    int countPrd_KeywordBrand(String keyword, String brandId);
}
