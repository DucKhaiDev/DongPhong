package Dao.deploy;

import Connect.DBConnect;
import Entity.Review;
import Services.deploy.ProductService;
import Services.deploy.UserService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDao implements Dao.ReviewDao {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    private final UserService userService = new UserService();
    private final ProductService productService = new ProductService();

    @Override
    public void insert(Review review) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("INSERT INTO [REVIEW](USER_ID, PRO_ID, REV_CONTENT, REV_DATE, REV_IMG) VALUES(?, ?, ?, ?, ?)");
            ps.setString(1, review.getUser().getUserId());
            ps.setString(2, review.getProduct().getProductId());
            ps.setString(3, review.getReviewContent());
            ps.setTimestamp(4, review.getReviewDate());
            ps.setString(5, review.getReviewImage());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public void edit(Review review) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("UPDATE [REVIEW] SET USER_ID = ?, PRO_ID = ?, REV_CONTENT = ?, REV_DATE = ?, REV_IMG = ? WHERE REV_ID = ?");
            ps.setString(1, review.getUser().getUserId());
            ps.setString(2, review.getProduct().getProductId());
            ps.setString(3, review.getReviewContent());
            ps.setTimestamp(4, review.getReviewDate());
            ps.setString(5, review.getReviewImage());
            ps.setInt(6, review.getReviewId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public void delete(int reviewId) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("DELETE FROM [REVIEW] WHERE REV_ID = ?");
            ps.setInt(1, reviewId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public Review getReview(int reviewId) {
        conn = DBConnect.getConnection();
        Review review = new Review();

        try {
            ps = conn.prepareStatement("SELECT * FROM [REVIEW] WHERE REV_ID = ?");
            ps.setInt(1, reviewId);
            rs = ps.executeQuery();
            rs.next();
            review.setReviewId(reviewId);
            review.setUser(userService.getUser(rs.getString("USER_ID").trim()));
            review.setProduct(productService.getProduct(rs.getString("PRO_ID").trim()));
            review.setReviewContent(rs.getString("REV_CONTENT"));
            review.setReviewDate(rs.getTimestamp("REV_DATE"));
            review.setReviewImage(rs.getString("REV_IMG"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return review;
    }

    @Override
    public List<Review> getAll() {
        conn = DBConnect.getConnection();
        List<Review> reviews = new ArrayList<>();

        try {
            ps = conn.prepareStatement("SELECT * FROM [REVIEW]");
            rs = ps.executeQuery();
            while (rs.next()) {
                Review review = new Review();
                review.setReviewId(rs.getInt("REV_ID"));
                review.setUser(userService.getUser(rs.getString("USER_ID").trim()));
                review.setProduct(productService.getProduct(rs.getString("PRO_ID").trim()));
                review.setReviewContent(rs.getString("REV_CONTENT"));
                review.setReviewDate(rs.getTimestamp("REV_DATE"));
                review.setReviewImage(rs.getString("REV_IMG"));

                reviews.add(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return reviews;
    }
}
