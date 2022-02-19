package Dao.deploy;

import Connect.DBConnect;
import Entity.ProImage;
import Services.deploy.ProductService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProImageDao implements Dao.ProImageDao {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    
    ProductService productService = new ProductService();

    @Override
    public void insert(ProImage image) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("INSERT INTO [PROIMAGE](IMG_NAME, PRO_ID) VALUES(?, ?)");
            ps.setString(1, image.getIMG_NAME());
            ps.setString(2, image.getPRO().getPRO_ID());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }
    }

    @Override
    public void delete(int IMG_ID) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("DELETE FROM [PROIMAGE] WHERE IMG_ID = ?");
            ps.setInt(1, IMG_ID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }
    }

    @Override
    public void delete(String PRO_ID) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("DELETE FROM [PROIMAGE] WHERE PRO_ID = ?");
            ps.setString(1, PRO_ID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }
    }

    @Override
    public ProImage getImage(int IMG_ID) {
        ProImage image = new ProImage();
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [PROIMAGE] WHERE IMG_ID =  ?");
            ps.setInt(1, IMG_ID);
            rs = ps.executeQuery();

            rs.next();
            image.setIMG_ID(rs.getInt("IMG_ID"));
            image.setIMG_NAME(rs.getString("IMG_NAME"));
            image.setPRO(productService.getProduct(rs.getString("PRO_ID")));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeResultSet(rs);
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }

        return image;
    }

    @Override
    public List<String> getProImage(String PRO_ID) {
        List<String> images = new ArrayList<>();
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT IMG_NAME FROM [PROIMAGE] WHERE PRO_ID = ? ORDER BY IMG_ID ASC");
            ps.setString(1, PRO_ID);
            rs = ps.executeQuery();

            while (rs.next()) {
                images.add(rs.getString("IMG_NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeResultSet(rs);
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }

        return images;
    }
}
