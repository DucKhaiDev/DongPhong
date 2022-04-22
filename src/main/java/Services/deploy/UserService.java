package Services.deploy;

import Dao.deploy.UserDao;
import Entity.User;
import Services.IUserService;

import java.util.List;
import java.util.UUID;

public class UserService implements IUserService {
    private final UserDao userDao = new UserDao();

    @Override
    public void insert(User user) {
        userDao.insert(user);
    }

    @Override
    public void edit(User user) {
        userDao.edit(user);
    }

    @Override
    public void delete(String identify) {
        userDao.delete(identify);
    }

    @Override
    public User getUser(String identify) {
        return userDao.getUser(identify);
    }

    @Override
    public User login(String username, String password) {
        User user = userDao.getUser(username);
        if (user != null && password.equals(user.getPassword())) {
            return user;
        }

        return null;
    }

    @Override
    public boolean register(String username, String password, String email) {
        if (checkExistUsername(username) || checkExistEmail(email)) {
            return false;
        }

        //Tạo tài khoản
        String userId = UUID.randomUUID().toString();
        User user = new User(userId, username, password, email);
        insert(user);

        return true;
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public List<User> search(String username) {
        return userDao.search(username);
    }

    @Override
    public boolean checkExistUsername(String username) {
        return userDao.checkExistUsername(username);
    }

    @Override
    public boolean checkExistEmail(String email) {
        return userDao.checkExistEmail(email);
    }

    @Override
    public int countAdmin() {
        return userDao.countAdmin();
    }
}
