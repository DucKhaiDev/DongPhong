package Dao;

import Entity.Product;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface IProductDao {
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

    int countProduct(String roomId);

    int countPrd_RoomBrand(String roomId, String brandId);

    List<Product> getProductByRoom(String roomId);

    List<Product> searchByNameInRoom(String roomId, String name);

    int countPrd_KeywordBrand(String keyword, String brandId);

    Map<Product, Integer> bestseller(Timestamp fromDate, Timestamp toDate);

    Map<Product, Integer> favourite();

    Map<Product, Double> highestRated();

    int countSale(String productId);

    int countOutOfStock();

    List<Product> getSortSaleAsc();

    List<Product> getSortSaleDesc();
}