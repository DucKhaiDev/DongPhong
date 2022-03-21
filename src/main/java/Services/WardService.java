package Services;

import Entity.Ward;

import java.util.List;

public interface WardService {
    List<Ward> getByDistrict(int districtId);
}
