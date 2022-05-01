package Dao;

import Entity.TempLink;
import Entity.User;

public interface ITempLinkDao {
    void insert(TempLink tempLink);

    void delete(String token);

    void delete(User user);

    TempLink getTempLink(String token);
}
