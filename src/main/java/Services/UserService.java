package Services;

import Entity.User;

import java.util.List;

public interface UserService {
    void insert(User user);
    void edit(User user);
    void delete(int USER_ID);
    User getUser(int USER_ID);
    User getUser(String USERNAME_OR_EMAIL);
    User login(String USERNAME, String PASSWORD);
    boolean register(String USERNAME, String PASSWORD, String EMAIL);
    List<User> getAll();
    List<User> search(String NAME);
    boolean checkExistUSERNAME(String USERNAME);
    boolean checkExistEMAIL(String EMAIL);
}
