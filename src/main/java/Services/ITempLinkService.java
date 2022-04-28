package Services;

import Entity.TempLink;

public interface ITempLinkService {
    void insert(TempLink tempLink);
    void delete(String token);
    TempLink getTempLink(String token);
}
