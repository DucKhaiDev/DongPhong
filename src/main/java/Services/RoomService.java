package Services;

import Entity.Room;

import java.util.List;

public interface RoomService {
    Room getRoom(String roomId);
    List<Room> getAll();
}
