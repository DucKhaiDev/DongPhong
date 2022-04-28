package Entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class TempLink implements Serializable {
    private static final long serialVersionUID = 1L;

    private String UUID;
    private User user;
    private Timestamp createDate;

    public TempLink() {
    }

    public TempLink(String UUID, User user, Timestamp createDate) {
        this.UUID = UUID;
        this.user = user;
        this.createDate = createDate;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
}