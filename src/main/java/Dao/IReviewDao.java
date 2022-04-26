package Dao;

import Entity.Product;
import Entity.Review;

import java.util.List;

public interface IReviewDao {
    void insert(Review review);

    void edit(Review review);

    void delete(int reviewId);

    Review getReview(int reviewId);

    List<Review> getAll();

    List<Review> getAll(String productId);

    int countReview(String productId);

    double checkRateStatus(String productId, String username);

    void syncRate(String productId, String username, double rate);

    List<Product> getAllProducts();

    int countRate(String productId);

    int countGoodRate(String productId);

    int countBadRate(String productId);
}