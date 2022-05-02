package Dao.deploy;

import Connect.DBConnect;
import Dao.IUserDao;
import Entity.User;
import Util.Constant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements IUserDao {
    @Override
    public String getUserId(String identify) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT USER_ID FROM [USER] WHERE USERNAME = ? OR EMAIL = ?");
            ps.setString(1, identify);
            ps.setString(2, identify);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("USER_ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
        return null;
    }

    @Override
    public void insert(User user) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("INSERT INTO [USER](USER_ID, FIRSTNAME, LASTNAME, USERNAME, PASSWORD, EMAIL, ADDRESS, PHONE, AVATAR, [ROLE], VC_CHAOMUNG) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
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
            ps.setBoolean(11, user.getVc_chaomung());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(null, ps, conn);
        }
    }

    @Override
    public void edit(User user) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("UPDATE [USER] " +
                    "SET FIRSTNAME = ?, LASTNAME = ?, USERNAME = ?, PASSWORD = ?, EMAIL = ?, ADDRESS = ?, PHONE = ?, AVATAR = ?, [ROLE] = ?, VC_CHAOMUNG = ? " +
                    "WHERE USER_ID = ?");
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getAddress());
            ps.setString(7, user.getPhone());
            ps.setString(8, user.getAvatar());
            ps.setBoolean(9, user.getRole());
            ps.setBoolean(10, user.getVc_chaomung());
            ps.setString(11, user.getUserId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(null, ps, conn);
        }
    }

    @Override
    public void delete(String identify) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("DELETE FROM [USER] WHERE USER_ID = ? OR USERNAME = ? OR EMAIL = ?");
            ps.setString(1, identify);
            ps.setString(2, identify);
            ps.setString(3, identify);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBConnect.closeAll(null, ps, conn);
        }
    }

    @Override
    public User getUser(String identify) {
        User user = new User();
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM [USER] WHERE USER_ID = ? OR USERNAME = ? OR EMAIL = ?");
            ps.setString(1, identify);
            ps.setString(2, identify);
            ps.setString(3, identify);
            rs = ps.executeQuery();
            if (rs.next()) {
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
                user.setVc_chaomung(rs.getBoolean("VC_CHAOMUNG"));
            }
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
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM [USER] ORDER BY [ROLE] ASC, USERNAME ASC");
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
                user.setVc_chaomung(rs.getBoolean("VC_CHAOMUNG"));

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
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

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
                user.setVc_chaomung(rs.getBoolean("VC_CHAOMUNG"));

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
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

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
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

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
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;

        try {
            ps = conn.prepareStatement("SELECT COUNT(*) FROM [USER] WHERE [ROLE] = 'False'");
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return count;
    }

    @Override
    public int countMember() {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;

        try {
            ps = conn.prepareStatement("SELECT COUNT(*) FROM [USER] WHERE [ROLE] = 'True'");
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return count;
    }

    @Override
    public List<User> getTopMember() {
        List<User> users = new ArrayList<>();
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT TOP(10) [USER].USER_ID, COUNT(ORD_ID) " +
                    "FROM dbo.[USER] " +
                    "JOIN dbo.[ORDER] ON [ORDER].USER_ID = [USER].USER_ID " +
                    "GROUP BY [USER].USER_ID " +
                    "ORDER BY COUNT(ORD_ID) DESC");
            rs = ps.executeQuery();
            while (rs.next()) {
                users.add(Constant.Service.USER_SERVICE.getUser(rs.getString(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return users;
    }
}