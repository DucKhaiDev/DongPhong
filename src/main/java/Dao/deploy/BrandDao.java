package Dao.deploy;

import Connect.DBConnect;
import Dao.IBrandDao;
import Entity.Brand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrandDao implements IBrandDao {
    @Override
    public void insert(Brand brand) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("INSERT INTO [BRAND](BRA_ID, BRA_NAME, BRA_DES) VALUES(?, ?, ?)");
            ps.setString(1, brand.getBrandId());
            ps.setString(2, brand.getBrandName());
            ps.setString(3, brand.getBrandDescription());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(null, ps, conn);
        }
    }

    @Override
    public void edit(Brand brand) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("UPDATE [BRAND] SET BRA_NAME = ?, BRA_DES = ? WHERE BRA_ID = ?");
            ps.setString(1, brand.getBrandName());
            ps.setString(2, brand.getBrandDescription());
            ps.setString(3, brand.getBrandId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(null, ps, conn);
        }
    }

    @Override
    public void delete(String brandId) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("DELETE FROM [BRAND] WHERE BRA_ID = ?");
            ps.setString(1, brandId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(null, ps, conn);
        }
    }

    @Override
    public Brand getBrand(String brandId) {
        Brand brand = new Brand();
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM [BRAND] WHERE BRA_ID = ?");
            ps.setString(1, brandId);
            rs = ps.executeQuery();

            if (rs.next()) {
                brand.setBrandId(rs.getString("BRA_ID").trim());
                brand.setBrandName(rs.getString("BRA_NAME"));
                brand.setBrandDescription(rs.getString("BRA_DES"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return brand;
    }

    @Override
    public List<Brand> getAll() {
        List<Brand> brands = new ArrayList<>();
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM [BRAND] ORDER BY BRA_ID");

            rs = ps.executeQuery();
            while (rs.next()) {
                Brand brand = new Brand();
                brand.setBrandId(rs.getString("BRA_ID").trim());
                brand.setBrandName(rs.getString("BRA_NAME"));
                brand.setBrandDescription(rs.getString("BRA_DES"));

                brands.add(brand);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return brands;
    }

    @Override
    public List<Brand> searchByName(String brandName) {
        List<Brand> brands = new ArrayList<>();
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM [BRAND] WHERE BRA_NAME LIKE ? ORDER BY BRA_NAME ASC");
            ps.setString(1, "%" + brandName + "%");

            rs = ps.executeQuery();
            while (rs.next()) {
                Brand brand = new Brand();
                brand.setBrandId(rs.getString("BRA_ID").trim());
                brand.setBrandName(rs.getString("BRA_NAME"));
                brand.setBrandDescription(rs.getString("BRA_DES"));

                brands.add(brand);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return brands;
    }

    @Override
    public boolean checkExistId(String id) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM [BRAND] WHERE BRA_ID = ?");
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return false;
    }

    @Override
    public boolean isUnusedBrand(String brandId) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM [PRODUCT] WHERE BRA_ID = ?");
            ps.setString(1, brandId);

            rs = ps.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return true;
    }
}