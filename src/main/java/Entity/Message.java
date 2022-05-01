package Entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    private int msgId;
    private String msgContent;
    private String msgEmail;
    private Timestamp msgDate;

    public Message() {
    }

    public Message(int msgId, String msgContent, String msgEmail, Timestamp msgDate) {
        this.msgId = msgId;
        this.msgContent = msgContent;
        this.msgEmail = msgEmail;
        this.msgDate = msgDate;
    }

    public Message(String msgContent, String msgEmail, Timestamp msgDate) {
        this.msgContent = msgContent;
        this.msgEmail = msgEmail;
        this.msgDate = msgDate;
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getMsgEmail() {
        return msgEmail;
    }

    public void setMsgEmail(String msgEmail) {
        this.msgEmail = msgEmail;
    }

    public Timestamp getMsgDate() {
        return msgDate;
    }

    public void setMsgDate(Timestamp msgDate) {
        this.msgDate = msgDate;
    }
}