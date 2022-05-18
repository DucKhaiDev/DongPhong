package Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TempLink implements Serializable {
    private static final long serialVersionUID = 1L;

    private String UUID;
    private User user;
    private Timestamp createDate;
}