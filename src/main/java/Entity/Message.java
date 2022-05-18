package Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    private int msgId;
    private String msgContent;
    private String msgEmail;
    private Timestamp msgDate;

    public Message(String msgContent, String msgEmail, Timestamp msgDate) {
        this.msgContent = msgContent;
        this.msgEmail = msgEmail;
        this.msgDate = msgDate;
    }
}