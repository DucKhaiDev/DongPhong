package Dao;

import Entity.Review;

import java.util.List;

public interface ReviewDao {
    void insert(Review review);
    void edit(Review review);
    void delete(int reviewId);
    Review getReview(int reviewId);
    List<Review> getAll();
}