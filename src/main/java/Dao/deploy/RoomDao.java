package Dao.deploy;

import Connect.DBConnect;
import Dao.IRoomDao;
import Entity.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDao implements IRoomDao {
    @Override
    public Room getRoom(String roomId) {
        Room room = new Room();
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM [ROOM] WHERE ROOM_ID = ?");
            ps.setString(1, roomId);
            rs = ps.executeQuery();
            if (rs.next()) {
                room.setRoomId(roomId);
                room.setRoomName(rs.getString("ROOM_NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return room;
    }

    @Override
    public List<Room> getAll() {
        List<Room> rooms = new ArrayList<>();
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM [ROOM] ORDER BY ROOM_ID ASC");
            rs = ps.executeQuery();
            while (rs.next()) {
                Room room = new Room();
                room.setRoomId(rs.getString("ROOM_ID").trim());
                room.setRoomName(rs.getString("ROOM_NAME"));
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return rooms;
    }
}
