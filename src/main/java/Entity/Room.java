package Entity;

import java.io.Serializable;

public class Room implements Serializable {
    private static final long serialVersionUID = 1L;

    private String ROOM_ID;
    private String ROOM_NAME;

    public String getROOM_ID() {
        return ROOM_ID;
    }

    public void setROOM_ID(String ROOM_ID) {
        this.ROOM_ID = ROOM_ID;
    }

    public String getROOM_NAME() {
        return ROOM_NAME;
    }

    public void setROOM_NAME(String ROOM_NAME) {
        this.ROOM_NAME = ROOM_NAME;
    }
}
