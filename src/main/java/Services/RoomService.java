package Services;

import Entity.Room;

import java.util.List;

public interface RoomService {
    Room getRoom(String ROOM_ID);
    List<Room> getAll();
}
