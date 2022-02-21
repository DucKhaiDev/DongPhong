package Services.deploy;

import Dao.deploy.UserDao;
import Entity.User;

import java.util.List;

public class UserService implements Services.UserService {
    UserDao userDao = new UserDao();

    @Override
    public void insert(User user) {
        userDao.insert(user);
    }

    @Override
    public void edit(User user) {
        userDao.edit(user);
    }

    @Override
    public void delete(int USER_ID) {
        userDao.delete(USER_ID);
    }

    @Override
    public User getUser(int USER_ID) {
        return userDao.getUser(USER_ID);
    }

    @Override
    public User getUser(String USERNAME_OR_EMAIL) {
        return userDao.getUser(USERNAME_OR_EMAIL);
    }

    @Override
    public User login(String USERNAME_OR_EMAIL, String PASSWORD) {
        User user = userDao.getUser(USERNAME_OR_EMAIL);
        if (user != null && PASSWORD.equals(user.getPASSWORD())) {
            return user;
        }

        return null;
    }

    @Override
    public boolean register(String USERNAME, String PASSWORD, String EMAIL) {
        if (checkExistUSERNAME(USERNAME) || checkExistEMAIL(EMAIL)) {
            return false;
        }
        insert(new User(USERNAME, PASSWORD, EMAIL));

        return true;
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public List<User> search(String NAME) {
        return userDao.search(NAME);
    }

    @Override
    public boolean checkExistUSERNAME(String USERNAME) {
        return userDao.checkExistUSERNAME(USERNAME);
    }

    @Override
    public boolean checkExistEMAIL(String EMAIL) {
        return userDao.checkExistEMAIL(EMAIL);
    }
}
