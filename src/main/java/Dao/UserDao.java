package Dao;

import Entity.User;

import java.util.List;

public interface UserDao {
    int getUSER_ID(String USERNAME_OR_EMAIL);
    void insert(User user);
    void edit(User user);
    void delete(int USER_ID);
    void delete(String USERNAME_or_EMAIL);
    User getUser(int USER_ID);
    User getUser(String USERNAME_or_EMAIL);
    List<User> getAll();
    List<User> search(String NAME);
    boolean checkExistUSERNAME(String USERNAME);
    boolean checkExistEMAIL(String EMAIL);
    int countAdmin();
}
