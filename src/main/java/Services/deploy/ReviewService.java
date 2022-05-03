package Services.deploy;

import Dao.deploy.ReviewDao;
import Entity.Product;
import Entity.Review;
import Services.IReviewService;

import java.sql.Timestamp;
import java.util.List;

public class ReviewService implements IReviewService {
    private final ReviewDao reviewDao = new ReviewDao();

    @Override
    public void insert(Review review) {
        reviewDao.insert(review);
    }

    @Override
    public void edit(Review review) {
        reviewDao.edit(review);
    }

    @Override
    public void delete(int reviewId) {
        reviewDao.delete(reviewId);
    }

    @Override
    public void deleteAll(String userId) {
        reviewDao.deleteAll(userId);
    }

    @Override
    public Review getReview(int reviewId) {
        return reviewDao.getReview(reviewId);
    }

    @Override
    public List<Review> getAll() {
        return reviewDao.getAll();
    }

    @Override
    public List<Review> getAll(String productId) {
        return reviewDao.getAll(productId);
    }

    public int countReview(String productId) {
        return reviewDao.countReview(productId);
    }

    @Override
    public double checkRateStatus(String productId, String username) {
        return reviewDao.checkRateStatus(productId, username);
    }

    @Override
    public void syncRate(String productId, String username, double rate) {
        reviewDao.syncRate(productId, username, rate);
    }

    @Override
    public List<Product> getAllProducts() {
        return reviewDao.getAllProducts();
    }

    @Override
    public int countRate(String productId) {
        return reviewDao.countRate(productId);
    }

    @Override
    public int countGoodRate(String productId) {
        return reviewDao.countGoodRate(productId);
    }

    @Override
    public int countBadRate(String productId) {
        return reviewDao.countBadRate(productId);
    }

    @Override
    public int countNewComment(Timestamp from) {
        return reviewDao.countNewComment(from);
    }
}