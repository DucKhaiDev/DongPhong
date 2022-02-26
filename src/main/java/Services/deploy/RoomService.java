package Services.deploy;

import Dao.deploy.RoomDao;
import Entity.Room;

import java.util.List;

public class RoomService implements Services.RoomService {
    private RoomDao roomDao = new RoomDao();

    @Override
    public Room getRoom(String ROOM_ID) {
        return roomDao.getRoom(ROOM_ID);
    }

    @Override
    public List<Room> getAll() {
        return roomDao.getAll();
    }
}
