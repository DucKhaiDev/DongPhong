package Dao.deploy;

import Connect.DBConnect;
import Dao.IProImageDao;
import Entity.ProImage;
import Util.Constant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProImageDao implements IProImageDao {
    @Override
    public void insert(ProImage image) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("INSERT INTO [PROIMAGE](IMG_NAME, PRO_ID) VALUES(?, ?)");
            ps.setString(1, image.getImageName());
            ps.setString(2, image.getProduct().getProductId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(null, ps, conn);
        }
    }

    @Override
    public void edit(ProImage image) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("UPDATE [PROIMAGE] SET IMG_NAME = ?, PRO_ID = ? WHERE IMG_ID = ?");
            ps.setString(1, image.getImageName());
            ps.setString(2, image.getProduct().getProductId());
            ps.setInt(3, image.getImageId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(null, ps, conn);
        }
    }

    @Override
    public void delete(int imageId) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("DELETE FROM [PROIMAGE] WHERE IMG_ID = ?");
            ps.setInt(1, imageId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(null, ps, conn);
        }
    }

    @Override
    public void delete(String productId) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("DELETE FROM [PROIMAGE] WHERE PRO_ID = ?");
            ps.setString(1, productId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(null, ps, conn);
        }
    }

    @Override
    public ProImage getImage(int imageId) {
        ProImage image = new ProImage();
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM [PROIMAGE] WHERE IMG_ID =  ?");
            ps.setInt(1, imageId);
            rs = ps.executeQuery();
            if (rs.next()) {
                image.setImageId(imageId);
                image.setImageName(rs.getString("IMG_NAME"));
                image.setProduct(Constant.Service.PRODUCT_SERVICE.getProduct(rs.getString("PRO_ID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return image;
    }

    @Override
    public List<ProImage> getProImage(String productId) {
        List<ProImage> images = new ArrayList<>();
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM [PROIMAGE] WHERE PRO_ID = ? ORDER BY IMG_ID ASC");
            ps.setString(1, productId);
            rs = ps.executeQuery();
            while (rs.next()) {
                ProImage image = new ProImage();
                image.setImageId(rs.getInt("IMG_ID"));
                image.setImageName(rs.getString("IMG_NAME"));
                image.setProduct(Constant.Service.PRODUCT_SERVICE.getProduct(productId));

                images.add(image);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return images;
    }

    @Override
    public List<ProImage> getAll() {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ProImage> images = new ArrayList<>();

        try {
            ps = conn.prepareStatement("SELECT * FROM [PROIMAGE] ORDER BY IMG_ID ASC");
            rs = ps.executeQuery();
            while (rs.next()) {
                ProImage image = new ProImage();
                image.setImageId(rs.getInt("IMG_ID"));
                image.setImageName(rs.getString("IMG_NAME"));
                image.setProduct(Constant.Service.PRODUCT_SERVICE.getProduct(rs.getString("PRO_ID").trim()));

                images.add(image);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return images;
    }
}