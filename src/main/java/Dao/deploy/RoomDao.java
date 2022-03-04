package Dao.deploy;

import Connect.DBConnect;
import Entity.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDao implements Dao.RoomDao {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    @Override
    public Room getRoom(String ROOM_ID) {
        Room room = new Room();
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [ROOM] WHERE ROOM_ID = ?");
            ps.setString(1, ROOM_ID);
            rs = ps.executeQuery();
            rs.next();
            room.setROOM_ID(rs.getString("ROOM_ID"));
            room.setROOM_NAME(rs.getString("ROOM_NAME"));
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
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [ROOM] ORDER BY ROOM_ID ASC");
            rs = ps.executeQuery();
            while (rs.next()) {
                Room room = new Room();
                room.setROOM_ID(rs.getString("ROOM_ID"));
                room.setROOM_NAME(rs.getString("ROOM_NAME"));
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
