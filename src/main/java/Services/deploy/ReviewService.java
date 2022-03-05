package Services.deploy;

import Dao.deploy.ReviewDao;
import Entity.Review;

import java.util.List;

public class ReviewService implements Services.ReviewService {
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
    public void delete(String REV_ID) {
        reviewDao.delete(REV_ID);
    }

    @Override
    public Review getReview(String REV_ID) {
        return reviewDao.getReview(REV_ID);
    }

    @Override
    public List<Review> getAll() {
        return reviewDao.getAll();
    }
}
