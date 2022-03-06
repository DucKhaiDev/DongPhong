package Dao;

import Entity.Room;

import java.util.List;

public interface RoomDao {
    Room getRoom(String roomId);
    List<Room> getAll();
}
