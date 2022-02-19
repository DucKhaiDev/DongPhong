package Dao.deploy;

import Connect.DBConnect;
import Entity.Brand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrandDao implements Dao.BrandDao {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    @Override
    public void insert(Brand brand) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("INSERT INTO [CATEGORY](CAT_ID, CAT_NAME, CAT_DES) VALUES(?, ?, ?)");
            ps.setString(1, brand.getBRA_ID());
            ps.setString(2, brand.getBRA_NAME());
            ps.setString(3, brand.getBRA_DES());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }
    }

    @Override
    public void edit(Brand brand) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("UPDATE [BRAND] SET BRA_NAME = ?, BRA_DES = ? WHERE BRA_ID = ?");
            ps.setString(1, brand.getBRA_NAME());
            ps.setString(2, brand.getBRA_DES());
            ps.setString(3, brand.getBRA_ID());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }
    }

    @Override
    public void delete(String BRA_ID) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("DELETE FROM [BRAND] WHERE BRA_ID = ?");
            ps.setString(1, BRA_ID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }
    }

    @Override
    public Brand getBrand(String BRA_ID) {
        Brand brand = new Brand();
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [BRAND] WHERE BRA_ID = ?");
            ps.setString(1, BRA_ID);
            rs = ps.executeQuery();

            rs.next();
            brand.setBRA_ID(rs.getString("BRA_ID"));
            brand.setBRA_NAME(rs.getString("BRA_NAME"));
            brand.setBRA_DES(rs.getString("BRA_DES"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeResultSet(rs);
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }

        return brand;
    }

    @Override
    public List<Brand> getAll() {
        List<Brand> brandList = new ArrayList<>();
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [BRAND]");

            rs = ps.executeQuery();
            while (rs.next()) {
                Brand brand = new Brand();
                brand.setBRA_ID(rs.getString("BRA_ID"));
                brand.setBRA_NAME(rs.getString("BRA_NAME"));
                brand.setBRA_DES(rs.getString("BRA_DES"));

                brandList.add(brand);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeResultSet(rs);
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }

        return brandList;
    }

    @Override
    public List<Brand> searchByName(String NAME) {
        List<Brand> brandList = new ArrayList<>();
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [BRAND] WHERE BRA_NAME LIKE ?");
            ps.setString(1, "%" + NAME + "%");

            rs = ps.executeQuery();
            while (rs.next()) {
                Brand brand = new Brand();
                brand.setBRA_ID(rs.getString("BRA_ID"));
                brand.setBRA_NAME(rs.getString("BRA_NAME"));
                brand.setBRA_DES(rs.getString("BRA_DES"));

                brandList.add(brand);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeResultSet(rs);
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }

        return brandList;
    }

    @Override
    public boolean checkExistID(String ID) {
        boolean exist = false;
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [BRAND] WHERE BRA_ID = ?");
            ps.setString(1, ID);

            rs = ps.executeQuery();
            if (rs.next()) {
                exist = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeResultSet(rs);
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }

        return exist;
    }
}
