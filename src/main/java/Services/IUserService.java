package Services;

import Entity.User;

import java.util.List;

public interface IUserService {
    void insert(User user);

    void edit(User user);

    void delete(String identify);

    User getUser(String identify);

    User login(String username, String password);

    boolean register(String username, String password, String email);

    List<User> getAll();

    List<User> search(String username);

    boolean checkExistUsername(String username);

    boolean checkExistEmail(String email);

    int countAdmin();

    int countMember();

    List<User> getTopMember();
}