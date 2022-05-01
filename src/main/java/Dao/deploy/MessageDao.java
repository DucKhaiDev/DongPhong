package Dao.deploy;

import Connect.DBConnect;
import Dao.IMessageDao;
import Entity.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDao implements IMessageDao {
    @Override
    public void insert(Message message) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("INSERT INTO dbo.[MESSAGE](MSG_CONTENT, MSG_EMAIL, MSG_DATE) VALUES(?, ?, ?)");
            ps.setString(1, message.getMsgContent());
            ps.setString(2, message.getMsgEmail());
            ps.setTimestamp(3, message.getMsgDate());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(null, ps, conn);
        }
    }

    @Override
    public void delete(int msgId) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("DELETE FROM dbo.[MESSAGE] WHERE MSG_ID = ?");
            ps.setInt(1, msgId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(null, ps, conn);
        }
    }

    @Override
    public List<Message> getAll() {
        List<Message> messages = new ArrayList<>();
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM dbo.[MESSAGE]");
            rs = ps.executeQuery();
            while (rs.next()) {
                Message message = new Message();
                message.setMsgId(rs.getInt("MSG_ID"));
                message.setMsgContent(rs.getString("MSG_CONTENT"));
                message.setMsgEmail(rs.getString("MSG_EMAIL"));
                message.setMsgDate(rs.getTimestamp("MSG_DATE"));

                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return messages;
    }

    @Override
    public int countNewMessage(Timestamp from) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT COUNT(*) " +
                    "FROM dbo.[MESSAGE] " +
                    "WHERE ? <= MSG_DATE");
            ps.setTimestamp(1, from);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return 0;
    }
}