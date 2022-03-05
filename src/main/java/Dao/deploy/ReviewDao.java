package Dao.deploy;

import Connect.DBConnect;
import Entity.Review;
import Services.deploy.CustomerService;
import Services.deploy.ProductService;
import com.oracle.wls.shaded.org.apache.regexp.RE;

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

    private final CustomerService customerService = new CustomerService();
    private final ProductService productService = new ProductService();

    @Override
    public void insert(Review review) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("INSERT INTO [REVIEW](CUS_ID, PRO_ID, REV_CONTENT, REV_DATE, REV_IMG) VALUES(?, ?, ?, ?, ?)");
            ps.setString(1, review.getCUS().getCUS_ID());
            ps.setString(2, review.getPRO().getPRO_ID());
            ps.setString(3, review.getREV_CONTENT());
            ps.setTimestamp(4, review.getREV_DATE());
            ps.setString(5, review.getREV_IMG());

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
            ps = conn.prepareStatement("UPDATE [REVIEW] SET CUS_ID = ?, PRO_ID = ?, REV_CONTENT = ?, REV_DATE = ?, REV_IMG = ? WHERE REV_ID = ?");
            ps.setString(1, review.getCUS().getCUS_ID());
            ps.setString(2, review.getPRO().getPRO_ID());
            ps.setString(3, review.getREV_CONTENT());
            ps.setTimestamp(4, review.getREV_DATE());
            ps.setString(5, review.getREV_IMG());
            ps.setInt(6, review.getREV_ID());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public void delete(String REV_ID) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("DELETE FROM [REVIEW] WHERE REV_ID = ?");
            ps.setString(1, REV_ID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public Review getReview(String REV_ID) {
        conn = DBConnect.getConnection();
        Review review = new Review();

        try {
            ps = conn.prepareStatement("SELECT * FROM [REVIEW] WHERE REV_ID = ?");
            ps.setString(1, REV_ID);
            rs = ps.executeQuery();
            rs.next();
            review.setREV_ID(rs.getInt("REV_ID"));
            review.setCUS(customerService.getCustomer(rs.getString("CUS_ID").trim()));
            review.setPRO(productService.getProduct(rs.getString("PRO_ID").trim()));
            review.setREV_CONTENT(rs.getString("REV_CONTENT"));
            review.setREV_DATE(rs.getTimestamp("REV_DATE"));
            review.setREV_IMG(rs.getString("REV_IMG"));
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
                review.setREV_ID(rs.getInt("REV_ID"));
                review.setCUS(customerService.getCustomer(rs.getString("CUS_ID").trim()));
                review.setPRO(productService.getProduct(rs.getString("PRO_ID").trim()));
                review.setREV_CONTENT(rs.getString("REV_CONTENT"));
                review.setREV_DATE(rs.getTimestamp("REV_DATE"));
                review.setREV_IMG(rs.getString("REV_IMG"));

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
