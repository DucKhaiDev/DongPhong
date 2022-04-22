package Dao;

import Entity.Room;

import java.util.List;

public interface IRoomDao {
    Room getRoom(String roomId);
    List<Room> getAll();
}
