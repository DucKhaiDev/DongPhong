package Dao;

import Entity.Review;

import java.util.List;

public interface ReviewDao {
    void insert(Review review);
    void edit(Review review);
    void delete(String REV_ID);
    Review getReview(String REV_ID);
    List<Review> getAll();
}
