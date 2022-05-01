package Services;

import Entity.Message;

import java.sql.Timestamp;
import java.util.List;

public interface IMessageService {
    void insert(Message message);
    void delete(int msgId);
    List<Message> getAll();
    int countNewMessage(Timestamp from);
}
