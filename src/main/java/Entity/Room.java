package Entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Room implements Serializable {
    private static final long serialVersionUID = 1L;

    private String roomId;
    private String roomName;
}