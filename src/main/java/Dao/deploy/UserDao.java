package Dao.deploy;

import Connect.DBConnect;
import Entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements Dao.UserDao {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    @Override
    public String getUserId(String identify) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT USER_ID FROM [USER] WHERE USERNAME = ? OR EMAIL = ?");
            ps.setString(1, identify);
            ps.setString(2, identify);
            rs = ps.executeQuery();
            rs.next();
            return rs.getString("USER_ID");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
        return null;
    }

    @Override
    public void insert(User user) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("INSERT INTO [USER](USER_ID, FIRSTNAME, LASTNAME, USERNAME, PASSWORD, EMAIL, ADDRESS, PHONE, AVATAR, [ROLE]) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, user.getUserId());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getUsername());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getEmail());
            ps.setString(7, user.getAddress());
            ps.setString(8, user.getPhone());
            ps.setString(9, user.getAvatar());
            ps.setBoolean(10, user.getRole());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public void edit(User user) {
        conn = DBConnect.getConnection();

        try {
             ps = conn.prepareStatement("UPDATE [USER] SET FIRSTNAME = ?, LASTNAME = ?, USERNAME = ?, PASSWORD = ?, EMAIL = ?, ADDRESS = ?, PHONE = ?, AVATAR = ?, [ROLE] = ? WHERE USER_ID = ?");
             ps.setString(1, user.getFirstName());
             ps.setString(2, user.getLastName());
             ps.setString(3, user.getUsername());
             ps.setString(4, user.getPassword());
             ps.setString(5, user.getEmail());
             ps.setString(6, user.getAddress());
             ps.setString(7, user.getPhone());
             ps.setString(8, user.getAvatar());
             ps.setBoolean(9, user.getRole());
             ps.setString(10, user.getUserId());
             ps.executeUpdate();
         } catch (SQLException e) {
             e.printStackTrace();
         } finally {
             DBConnect.closeAll(rs, ps, conn);
         }
    }

    @Override
    public void delete(String identify) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("DELETE FROM [USER] WHERE USER_ID = ? OR USERNAME = ? OR EMAIL = ?");
            ps.setString(1, identify);
            ps.setString(2, identify);
            ps.setString(3, identify);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public User getUser(String identify) {
        User user = new User();
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [USER] WHERE USER_ID = ? OR USERNAME = ? OR EMAIL = ?");
            ps.setString(1, identify);
            ps.setString(2, identify);
            ps.setString(3, identify);
            rs = ps.executeQuery();

            rs.next();
            user.setUserId(rs.getString("USER_ID"));
            user.setFirstName(rs.getString("FIRSTNAME"));
            user.setLastName(rs.getString("LASTNAME"));
            user.setUsername(rs.getString("USERNAME"));
            user.setPassword(rs.getString("PASSWORD"));
            user.setEmail(rs.getString("EMAIL"));
            user.setAddress(rs.getString("ADDRESS"));
            user.setPhone(rs.getString("PHONE"));
            user.setAvatar(rs.getString("AVATAR"));
            user.setRole(rs.getBoolean("ROLE"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [USER] ORDER BY [ROLE] ASC");

            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getString("USER_ID"));
                user.setFirstName(rs.getString("FIRSTNAME"));
                user.setLastName(rs.getString("LASTNAME"));
                user.setUsername(rs.getString("USERNAME"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setEmail(rs.getString("EMAIL"));
                user.setAddress(rs.getString("ADDRESS"));
                user.setPhone(rs.getString("PHONE"));
                user.setAvatar(rs.getString("AVATAR"));
                user.setRole(rs.getBoolean("ROLE"));

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return users;
    }

    @Override
    public List<User> search(String username) {
        List<User> users = new ArrayList<>();
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [USER] WHERE USERNAME LIKE ? ORDER BY USER_ID ASC");
            ps.setString(1, "%" + username + "%");

            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getString("USER_ID"));
                user.setFirstName(rs.getString("FIRSTNAME"));
                user.setLastName(rs.getString("LASTNAME"));
                user.setUsername(rs.getString("USERNAME"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setEmail(rs.getString("EMAIL"));
                user.setAddress(rs.getString("ADDRESS"));
                user.setPhone(rs.getString("PHONE"));
                user.setAvatar(rs.getString("AVATAR"));
                user.setRole(rs.getBoolean("ROLE"));

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return users;
    }

    @Override
    public boolean checkExistUsername(String username) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [USER] WHERE USERNAME = ?");
            ps.setString(1, username);

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
    public boolean checkExistEmail(String email) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [USER] WHERE EMAIL = ?");
            ps.setString(1, email);

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
    public int countAdmin() {
        conn = DBConnect.getConnection();
        int count = 0;

        try {
            ps = conn.prepareStatement("SELECT COUNT(*) FROM [USER] WHERE [ROLE] = 'False'");
            rs = ps.executeQuery();
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return count;
    }
}
