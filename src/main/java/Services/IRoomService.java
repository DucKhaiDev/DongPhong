package Services;

import Entity.Room;

import java.util.List;

public interface IRoomService {
    Room getRoom(String roomId);

    List<Room> getAll();
}
