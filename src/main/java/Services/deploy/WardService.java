package Services.deploy;

import Dao.deploy.WardDao;
import Entity.Ward;

import java.util.List;

public class WardService implements Services.WardService {
    @Override
    public List<Ward> getByDistrict(int districtId) {
        return new WardDao().getByDistrict(districtId);
    }
}
