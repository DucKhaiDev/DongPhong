package DaoImpl;

import Connect.DBConnect;
import DaoIntf.UserDaoIntf;
import Entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDaoIntf {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    @Override
    public int getUSER_ID(String USERNAME_OR_EMAIL) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT USER_ID FROM [USER] WHERE USERNAME = ? OR EMAIL = ?");
            ps.setString(1, USERNAME_OR_EMAIL);
            ps.setString(2, USERNAME_OR_EMAIL);
            rs = ps.executeQuery();
            rs.next();
            return rs.getInt("USER_ID");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBConnect.closeResultSet(rs);
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }
        return 0;
    }

    @Override
    public void insert(User user) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("INSERT INTO [USER](FIRSTNAME, LASTNAME, USERNAME, PASSWORD, EMAIL, ADDRESS, PHONE, AVATAR, ROLE) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, user.getFIRSTNAME());
            ps.setString(2, user.getLASTNAME());
            ps.setString(3, user.getUSERNAME());
            ps.setString(4, user.getPASSWORD());
            ps.setString(5, user.getEMAIL());
            ps.setString(6, user.getADDRESS());
            ps.setString(7, user.getPHONE());
            ps.setString(8, user.getAVATAR());
            ps.setBoolean(9, user.getROLE());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }
    }

    @Override
    public void edit(User user) {
        conn = DBConnect.getConnection();

        try {
             ps = conn.prepareStatement("UPDATE [USER] SET FIRSTNAME = ?, LASTNAME = ?, USERNAME = ?, PASSWORD = ?, EMAIL = ?, ADDRESS = ?, PHONE = ?, AVATAR = ?, ROLE = ? WHERE USER_ID = ?");
             ps.setString(1, user.getFIRSTNAME());
             ps.setString(2, user.getLASTNAME());
             ps.setString(3, user.getUSERNAME());
             ps.setString(4, user.getPASSWORD());
             ps.setString(5, user.getEMAIL());
             ps.setString(6, user.getADDRESS());
             ps.setString(7, user.getPHONE());
             ps.setString(8, user.getAVATAR());
             ps.setBoolean(9, user.getROLE());
             ps.setInt(10, user.getUSER_ID());
             ps.executeUpdate();
         } catch (SQLException ex) {
             ex.printStackTrace();
         } finally {
             DBConnect.closePreparedStatement(ps);
             DBConnect.closeConnection(conn);
         }
    }

    @Override
    public void delete(int USER_ID) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("DELETE FROM [USER] WHERE USER_ID = ?");
            ps.setInt(1, USER_ID);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }
    }

    @Override
    public void delete(String USERNAME_OR_EMAIL) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("DELETE FROM [USER] WHERE USERNAME = ? OR EMAIL = ?");
            ps.setString(1, USERNAME_OR_EMAIL);
            ps.setString(2, USERNAME_OR_EMAIL);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }
    }

    @Override
    public User getUser(int USER_ID) {
        User user = new User();
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [USER] WHERE USER_ID = ?");
            ps.setInt(1, USER_ID);
            rs = ps.executeQuery();

            rs.next();
            user.setFIRSTNAME(rs.getString("FIRSTNAME"));
            user.setLASTNAME(rs.getString("LASTNAME"));
            user.setUSER_ID(rs.getInt("USER_ID"));
            user.setUSERNAME(rs.getString("USERNAME"));
            user.setPASSWORD(rs.getString("PASSWORD"));
            user.setEMAIL(rs.getString("EMAIL"));
            user.setADDRESS(rs.getString("ADDRESS"));
            user.setPHONE(rs.getString("PHONE"));
            user.setAVATAR(rs.getString("AVATAR"));
            user.setROLE(rs.getBoolean("ROLE"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBConnect.closeResultSet(rs);
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }

        return user;
    }

    @Override
    public User getUser(String USERNAME_OR_EMAIL) {
        User user = new User();
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [USER] WHERE USERNAME = ? OR EMAIL = ?");
            ps.setString(1, USERNAME_OR_EMAIL);
            ps.setString(2, USERNAME_OR_EMAIL);
            rs = ps.executeQuery();

            rs.next();
            user.setFIRSTNAME(rs.getString("FIRSTNAME"));
            user.setLASTNAME(rs.getString("LASTNAME"));
            user.setUSER_ID(rs.getInt("USER_ID"));
            user.setUSERNAME(rs.getString("USERNAME"));
            user.setPASSWORD(rs.getString("PASSWORD"));
            user.setEMAIL(rs.getString("EMAIL"));
            user.setADDRESS(rs.getString("ADDRESS"));
            user.setPHONE(rs.getString("PHONE"));
            user.setAVATAR(rs.getString("AVATAR"));
            user.setROLE(rs.getBoolean("ROLE"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBConnect.closeResultSet(rs);
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }

        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [USER]");

            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setFIRSTNAME(rs.getString("FIRSTNAME"));
                user.setLASTNAME(rs.getString("LASTNAME"));
                user.setUSER_ID(rs.getInt("USER_ID"));
                user.setUSERNAME(rs.getString("USERNAME"));
                user.setPASSWORD(rs.getString("PASSWORD"));
                user.setEMAIL(rs.getString("EMAIL"));
                user.setADDRESS(rs.getString("ADDRESS"));
                user.setPHONE(rs.getString("PHONE"));
                user.setAVATAR(rs.getString("AVATAR"));
                user.setROLE(rs.getBoolean("ROLE"));

                userList.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBConnect.closeResultSet(rs);
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }

        return userList;
    }

    @Override
    public List<User> search(String keyword) {
        List<User> userList = new ArrayList<>();
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [USER] WHERE USERNAME LIKE ?");
            ps.setString(1, "%" + keyword + "%");

            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setFIRSTNAME(rs.getString("FIRSTNAME"));
                user.setLASTNAME(rs.getString("LASTNAME"));
                user.setUSER_ID(rs.getInt("USER_ID"));
                user.setUSERNAME(rs.getString("USERNAME"));
                user.setPASSWORD(rs.getString("PASSWORD"));
                user.setEMAIL(rs.getString("EMAIL"));
                user.setADDRESS(rs.getString("ADDRESS"));
                user.setPHONE(rs.getString("PHONE"));
                user.setAVATAR(rs.getString("AVATAR"));
                user.setROLE(rs.getBoolean("ROLE"));

                userList.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBConnect.closeResultSet(rs);
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }

        return userList;
    }

    @Override
    public boolean checkExistUSERNAME(String USERNAME) {
        boolean exist = false;
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [USER] WHERE USERNAME = ?");
            ps.setString(1, USERNAME);

            rs = ps.executeQuery();
            if (rs.next()) {
                exist = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBConnect.closeResultSet(rs);
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }

        return exist;
    }

    @Override
    public boolean checkExistEMAIL(String EMAIL) {
        boolean exist = false;
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [USER] WHERE EMAIL = ?");
            ps.setString(1, EMAIL);

            rs = ps.executeQuery();
            if (rs.next()) {
                exist = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBConnect.closeResultSet(rs);
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(conn);
        }

        return exist;
    }

    //Test
    public static void main(String[] args) {
        UserDaoImpl obj = new UserDaoImpl();

//        User user = new User("user_test", "user_test", "user_test@email.com", "9999999999", null, true);
//        obj.insert(user);

//        user.setUSER_ID(obj.getUSER_ID(user.getUSERNAME()));
//        System.out.println(user.getUSER_ID());

//        user.setPHONE("8888888888");
//        user.setEMAIL("user_test_edit@email.com");
//        obj.edit(user);

//        obj.delete(user.getUSER_ID());

//        User user1 = obj.getUser(10000000);
//        System.out.println(user1.getUSERNAME());
//        User user2 = obj.getUser("user");
//        System.out.println(user2.getUSER_ID());

//        List<User> userList = obj.getAll();
//        System.out.println(userList.size());

//        List<User> userList = obj.search("user");
//        System.out.println(userList.size());

//        System.out.println(obj.checkExistUSERNAME("user"));
//        System.out.println(obj.checkExistUSERNAME("user2"));
//        System.out.println(obj.checkExistEMAIL("user@email.com"));
//        System.out.println(obj.checkExistEMAIL("email@email.com"));
    }
}
