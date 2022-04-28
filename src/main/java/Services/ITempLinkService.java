package Services;

import Entity.TempLink;
import Entity.User;

public interface ITempLinkService {
    void insert(TempLink tempLink);
    void delete(String token);
    void delete(User user);
    TempLink getTempLink(String token);
}
