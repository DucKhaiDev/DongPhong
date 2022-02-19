package Dao.deploy;

import Connect.DBConnect;
import Entity.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao implements Dao.CategoryDao {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    @Override
    public void insert(Category category) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("INSERT INTO [CATEGORY](CAT_ID, CAT_NAME, CAT_DES) VALUES(?, ?, ?)");
            ps.setString(1, category.getCAT_ID());
            ps.setString(2, category.getCAT_NAME());
            ps.setString(3, category.getCAT_DES());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }
    }

    @Override
    public void edit(Category category) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("UPDATE [CATEGORY] SET CAT_NAME = ?, CAT_DES = ? WHERE CAT_ID = ?");
            ps.setString(1, category.getCAT_NAME());
            ps.setString(2, category.getCAT_DES());
            ps.setString(3, category.getCAT_ID());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }
    }

    @Override
    public void delete(String CAT_ID) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("DELETE FROM [CATEGORY] WHERE CAT_ID = ?");
            ps.setString(1, CAT_ID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }
    }

    @Override
    public Category getCategory(String CAT_ID) {
        Category category = new Category();
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [CATEGORY] WHERE CAT_ID = ?");
            ps.setString(1, CAT_ID);
            rs = ps.executeQuery();

            rs.next();
            category.setCAT_ID(rs.getString("CAT_ID"));
            category.setCAT_NAME(rs.getString("CAT_NAME"));
            category.setCAT_DES(rs.getString("CAT_DES"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeResultSet(rs);
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }

        return category;
    }

    @Override
    public List<Category> getAll() {
        List<Category> categoryList = new ArrayList<>();
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [CATEGORY]");

            rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setCAT_ID(rs.getString("CAT_ID"));
                category.setCAT_NAME(rs.getString("CAT_NAME"));
                category.setCAT_DES(rs.getString("CAT_DES"));

                categoryList.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeResultSet(rs);
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }

        return categoryList;
    }

    @Override
    public List<Category> searchByName(String NAME) {
        List<Category> categoryList = new ArrayList<>();
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [CATEGORY] WHERE CAT_NAME LIKE ?");
            ps.setString(1, "%" + NAME + "%");

            rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setCAT_ID(rs.getString("CAT_ID"));
                category.setCAT_NAME(rs.getString("CAT_NAME"));
                category.setCAT_DES(rs.getString("CAT_DES"));

                categoryList.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeResultSet(rs);
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }

        return categoryList;
    }

    @Override
    public boolean checkExistID(String ID) {
        boolean exist = false;
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [CATEGORY] WHERE CAT_ID = ?");
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
