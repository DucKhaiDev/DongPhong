package Dao.deploy;

import Connect.DBConnect;
import Dao.ITempLinkDao;
import Entity.TempLink;
import Entity.User;
import Util.Constant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TempLinkDao implements ITempLinkDao {
    @Override
    public void insert(TempLink tempLink) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("INSERT INTO dbo.[TEMPLINK] VALUES(?, ?, ?)");
            ps.setString(1, tempLink.getUUID());
            ps.setString(2, tempLink.getUser().getUsername());
            ps.setTimestamp(3, tempLink.getCreateDate());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(null, ps, conn);
        }
    }

    @Override
    public void delete(String token) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("DELETE FROM dbo.[TEMPLINK] WHERE UUID = ?");
            ps.setString(1, token);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(null, ps, conn);
        }
    }

    @Override
    public void delete(User user) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("DELETE FROM dbo.[TEMPLINK] WHERE USERNAME = ?");
            ps.setString(1, user.getUsername());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(null, ps, conn);
        }
    }

    @Override
    public TempLink getTempLink(String token) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM dbo.[TEMPLINK] WHERE UUID = ?");
            ps.setString(1, token);
            rs = ps.executeQuery();
            if (rs.next()) {
                TempLink tempLink = new TempLink();
                tempLink.setUUID(rs.getString("UUID"));
                tempLink.setUser(Constant.Service.USER_SERVICE.getUser(rs.getString("USERNAME")));
                tempLink.setCreateDate(rs.getTimestamp("CREATE_DATE"));
                return tempLink;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return null;
    }
}