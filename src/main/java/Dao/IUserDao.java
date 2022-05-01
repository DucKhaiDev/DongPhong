package Dao;

import Entity.User;

import java.util.List;

public interface IUserDao {
    String getUserId(String identify);

    void insert(User user);

    void edit(User user);

    void delete(String identify);

    User getUser(String identify);

    List<User> getAll();

    List<User> search(String username);

    boolean checkExistUsername(String username);

    boolean checkExistEmail(String email);

    int countAdmin();

    int countMember();

    List<User> getTopMember();
}