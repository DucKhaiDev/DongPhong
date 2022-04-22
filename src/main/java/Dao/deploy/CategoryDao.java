package Dao.deploy;

import Connect.DBConnect;
import Dao.ICategoryDao;
import Entity.Category;
import Services.deploy.RoomService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao implements ICategoryDao {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    private final RoomService roomService = new RoomService();

    @Override
    public void insert(Category category) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("INSERT INTO [CATEGORY](CAT_ID, CAT_NAME, ROOM_ID, CAT_DES) VALUES(?, ?, ?, ?)");
            ps.setString(1, category.getCategoryId());
            ps.setString(2, category.getCategoryName());
            ps.setString(3, category.getRoom().getRoomId());
            ps.setString(4, category.getCategoryDescription());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public void edit(Category category) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("UPDATE [CATEGORY] SET CAT_NAME = ?, ROOM_ID = ?, CAT_DES = ? WHERE CAT_ID = ?");
            ps.setString(1, category.getCategoryName());
            ps.setString(2, category.getRoom().getRoomId());
            ps.setString(3, category.getCategoryDescription());
            ps.setString(4, category.getCategoryId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public void delete(String categoryId) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("DELETE FROM [CATEGORY] WHERE CAT_ID = ?");
            ps.setString(1, categoryId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public Category getCategory(String categoryId) {
        Category category = new Category();
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [CATEGORY] WHERE CAT_ID = ?");
            ps.setString(1, categoryId);
            rs = ps.executeQuery();

            rs.next();
            category.setCategoryId(rs.getString("CAT_ID").trim());
            category.setCategoryName(rs.getString("CAT_NAME"));
            category.setRoom(roomService.getRoom(rs.getString("ROOM_ID").trim()));
            category.setCategoryDescription(rs.getString("CAT_DES"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return category;
    }

    @Override
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [CATEGORY] ORDER BY CAT_ID ASC");

            rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getString("CAT_ID").trim());
                category.setCategoryName(rs.getString("CAT_NAME"));
                category.setRoom(roomService.getRoom(rs.getString("ROOM_ID").trim()));
                category.setCategoryDescription(rs.getString("CAT_DES"));

                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return categories;
    }

    @Override
    public List<Category> getCategoryByRoom(String roomId) {
        List<Category> categories = new ArrayList<>();
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [CATEGORY] WHERE ROOM_ID = ? ORDER BY CAT_ID ASC");
            ps.setString(1, roomId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getString("CAT_ID").trim());
                category.setCategoryName(rs.getString("CAT_NAME"));
                category.setRoom(roomService.getRoom(rs.getString("ROOM_ID").trim()));
                category.setCategoryDescription(rs.getString("CAT_DES"));

                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return categories;
    }

    @Override
    public List<Category> searchByName(String categoryName) {
        List<Category> categories = new ArrayList<>();
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [CATEGORY] WHERE CAT_NAME LIKE ? ORDER BY CAT_ID ASC");
            ps.setString(1, "%" + categoryName + "%");

            rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getString("CAT_ID").trim());
                category.setCategoryName(rs.getString("CAT_NAME"));
                category.setRoom(roomService.getRoom(rs.getString("ROOM_ID").trim()));
                category.setCategoryDescription(rs.getString("CAT_DES"));

                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return categories;
    }

    @Override
    public boolean checkExistId(String id) {
        boolean exist = false;
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [CATEGORY] WHERE CAT_ID = ?");
            ps.setString(1, id);

            rs = ps.executeQuery();
            if (rs.next()) {
                exist = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return exist;
    }

    @Override
    public boolean isUnusedCategory(String categoryId) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [PRODUCT] WHERE CAT_ID = ?");
            ps.setString(1, categoryId);

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
