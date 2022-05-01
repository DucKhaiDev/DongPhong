package Services.deploy;

import Dao.deploy.MessageDao;
import Entity.Message;
import Services.IMessageService;

import java.sql.Timestamp;
import java.util.List;

public class MessageService implements IMessageService {
    private final MessageDao messageDao = new MessageDao();

    @Override
    public void insert(Message message) {
        messageDao.insert(message);
    }

    @Override
    public void delete(int msgId) {
        messageDao.delete(msgId);
    }

    @Override
    public List<Message> getAll() {
        return messageDao.getAll();
    }

    @Override
    public int countNewMessage(Timestamp from) {
        return messageDao.countNewMessage(from);
    }
}