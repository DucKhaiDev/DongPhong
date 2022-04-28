package Dao;

import Entity.TempLink;

public interface ITempLinkDao {
    void insert(TempLink tempLink);
    void delete(String token);
    TempLink getTempLink(String token);
}
