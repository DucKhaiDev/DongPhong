package Dao.deploy;

import Connect.DBConnect;
import Dao.IReviewDao;
import Entity.Product;
import Entity.Review;
import Util.Constant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDao implements IReviewDao {
    @Override
    public void insert(Review review) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("INSERT INTO [REVIEW](USER_ID, PRO_ID, REV_RATE, REV_CONTENT, REV_DATE, IS_RATE) VALUES(?, ?, ?, ?, ?, ?)");
            ps.setString(1, review.getUser().getUserId());
            ps.setString(2, review.getProduct().getProductId());
            ps.setDouble(3, review.getReviewRate());
            ps.setString(4, review.getReviewContent());
            ps.setTimestamp(5, review.getReviewDate());
            ps.setBoolean(6, review.isRate());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(null, ps, conn);
        }
    }

    @Override
    public void edit(Review review) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("UPDATE [REVIEW] SET USER_ID = ?, PRO_ID = ?, REV_RATE = ?, REV_CONTENT = ?, REV_DATE = ?, IS_RATE = ? WHERE REV_ID = ?");
            ps.setString(1, review.getUser().getUserId());
            ps.setString(2, review.getProduct().getProductId());
            ps.setDouble(3, review.getReviewRate());
            ps.setString(4, review.getReviewContent());
            ps.setTimestamp(5, review.getReviewDate());
            ps.setBoolean(6, review.isRate());
            ps.setInt(7, review.getReviewId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(null, ps, conn);
        }
    }

    @Override
    public void delete(int reviewId) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("DELETE FROM [REVIEW] WHERE REV_ID = ?");
            ps.setInt(1, reviewId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(null, ps, conn);
        }
    }

    @Override
    public Review getReview(int reviewId) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Review review = new Review();

        try {
            ps = conn.prepareStatement("SELECT * FROM [REVIEW] WHERE REV_ID = ?");
            ps.setInt(1, reviewId);
            rs = ps.executeQuery();
            if (rs.next()) {
                review.setReviewId(reviewId);
                review.setUser(Constant.Service.USER_SERVICE.getUser(rs.getString("USER_ID")));
                review.setProduct(Constant.Service.PRODUCT_SERVICE.getProduct(rs.getString("PRO_ID").trim()));
                review.setReviewRate(rs.getDouble("REV_RATE"));
                review.setReviewContent(rs.getString("REV_CONTENT"));
                review.setReviewDate(rs.getTimestamp("REV_DATE"));
                review.setRateStatus(rs.getBoolean("IS_RATE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return review;
    }

    @Override
    public List<Review> getAll() {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Review> reviews = new ArrayList<>();

        try {
            ps = conn.prepareStatement("SELECT * FROM [REVIEW]");
            rs = ps.executeQuery();
            while (rs.next()) {
                Review review = new Review();
                review.setReviewId(rs.getInt("REV_ID"));
                review.setUser(Constant.Service.USER_SERVICE.getUser(rs.getString("USER_ID")));
                review.setProduct(Constant.Service.PRODUCT_SERVICE.getProduct(rs.getString("PRO_ID").trim()));
                review.setReviewRate(rs.getDouble("REV_RATE"));
                review.setReviewContent(rs.getString("REV_CONTENT"));
                review.setReviewDate(rs.getTimestamp("REV_DATE"));
                review.setRateStatus(rs.getBoolean("IS_RATE"));

                reviews.add(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return reviews;
    }

    @Override
    public List<Review> getAll(String productId) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Review> reviews = new ArrayList<>();

        try {
            ps = conn.prepareStatement("SELECT * FROM [REVIEW] WHERE PRO_ID = ? ORDER BY REV_ID");
            ps.setString(1, productId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Review review = new Review();
                review.setReviewId(rs.getInt("REV_ID"));
                review.setUser(Constant.Service.USER_SERVICE.getUser(rs.getString("USER_ID")));
                review.setProduct(Constant.Service.PRODUCT_SERVICE.getProduct(rs.getString("PRO_ID").trim()));
                review.setReviewRate(rs.getDouble("REV_RATE"));
                review.setReviewContent(rs.getString("REV_CONTENT"));
                review.setReviewDate(rs.getTimestamp("REV_DATE"));
                review.setRateStatus(rs.getBoolean("IS_RATE"));

                reviews.add(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return reviews;
    }

    @Override
    public int countReview(String productId) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT COUNT(*) FROM [REVIEW] WHERE PRO_ID = ?");
            ps.setString(1, productId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return 0;
    }

    @Override
    public double checkRateStatus(String productId, String username) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT TOP(1) REV_RATE FROM [REVIEW] WHERE PRO_ID = ? AND USER_ID = ? AND IS_RATE = 'TRUE'");
            ps.setString(1, productId);
            ps.setString(2, Constant.Service.USER_SERVICE.getUser(username).getUserId());
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("REV_RATE");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return 0;
    }

    @Override
    public void syncRate(String productId, String username, double rate) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("UPDATE [REVIEW] SET REV_RATE = ? WHERE PRO_ID = ? AND USER_ID = ? AND IS_RATE = 'FALSE'");
            ps.setDouble(1, rate);
            ps.setString(2, productId);
            ps.setString(3, Constant.Service.USER_SERVICE.getUser(username).getUserId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(null, ps, conn);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Product> products = new ArrayList<>();

        try {
            ps = conn.prepareStatement("SELECT PRO_ID FROM [REVIEW] GROUP BY PRO_ID");
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = Constant.Service.PRODUCT_SERVICE.getProduct(rs.getString("PRO_ID"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return products;
    }

    @Override
    public int countRate(String productId) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT COUNT(*) FROM (SELECT USER_ID FROM [REVIEW] WHERE PRO_ID = ? AND IS_RATE = 'TRUE' GROUP BY USER_ID) AS COUNT_RATE");
            ps.setString(1, productId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return 0;
    }

    @Override
    public int countGoodRate(String productId) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT COUNT(*) FROM (SELECT USER_ID FROM [REVIEW] WHERE PRO_ID = ? AND REV_RATE >= 4 GROUP BY USER_ID) AS COUNT_GOOD_RATE");
            ps.setString(1, productId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return 0;
    }

    @Override
    public int countBadRate(String productId) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT COUNT(*) FROM (SELECT USER_ID FROM [REVIEW] WHERE PRO_ID = ? AND REV_RATE < 4 GROUP BY USER_ID) AS COUNT_GOOD_RATE");
            ps.setString(1, productId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return 0;
    }
}