package Dao.deploy;

import Connect.DBConnect;
import Entity.Category;
import Services.deploy.RoomService;

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

    private RoomService roomService = new RoomService();

    @Override
    public void insert(Category category) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("INSERT INTO [CATEGORY](CAT_ID, CAT_NAME, ROOM_ID, CAT_DES) VALUES(?, ?, ?, ?)");
            ps.setString(1, category.getCAT_ID());
            ps.setString(2, category.getCAT_NAME());
            ps.setString(3, category.getROOM().getROOM_ID());
            ps.setString(4, category.getCAT_DES());
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
            ps = conn.prepareStatement("UPDATE [CATEGORY] SET CAT_NAME = ?, ROOM_ID = ?, CAT_DES = ? WHERE CAT_ID = ?");
            ps.setString(1, category.getCAT_NAME());
            ps.setString(2, category.getROOM().getROOM_ID());
            ps.setString(3, category.getCAT_DES());
            ps.setString(4, category.getCAT_ID());
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
            category.setROOM(roomService.getRoom(rs.getString("ROOM_ID")));
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
        List<Category> categories = new ArrayList<>();
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [CATEGORY]");

            rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setCAT_ID(rs.getString("CAT_ID"));
                category.setCAT_NAME(rs.getString("CAT_NAME"));
                category.setROOM(roomService.getRoom(rs.getString("ROOM_ID")));
                category.setCAT_DES(rs.getString("CAT_DES"));

                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeResultSet(rs);
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }

        return categories;
    }

    @Override
    public List<Category> getCategoryByRoom(String ROOM_ID) {
        List<Category> categories = new ArrayList<>();
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [CATEGORY] WHERE ROOM_ID = ? ORDER BY CAT_NAME ASC");
            ps.setString(1, ROOM_ID);
            rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setCAT_ID(rs.getString("CAT_ID"));
                category.setCAT_NAME(rs.getString("CAT_NAME"));
                category.setROOM(roomService.getRoom(rs.getString("ROOM_ID")));
                category.setCAT_DES(rs.getString("CAT_DES"));

                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeResultSet(rs);
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }

        return categories;
    }

    @Override
    public List<Category> searchByName(String NAME) {
        List<Category> categories = new ArrayList<>();
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [CATEGORY] WHERE CAT_NAME LIKE ?");
            ps.setString(1, "%" + NAME + "%");

            rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setCAT_ID(rs.getString("CAT_ID"));
                category.setCAT_NAME(rs.getString("CAT_NAME"));
                category.setROOM(roomService.getRoom(rs.getString("ROOM_ID")));
                category.setCAT_DES(rs.getString("CAT_DES"));

                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeResultSet(rs);
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }

        return categories;
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

    @Override
    public boolean isUnusedCategory(String CAT_ID) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [PRODUCT] WHERE CAT_ID = ?");
            ps.setString(1, CAT_ID);

            rs = ps.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeResultSet(rs);
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }

        return true;
    }
}
