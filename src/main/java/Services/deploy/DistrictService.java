package Services.deploy;

import Dao.deploy.DistrictDao;
import Entity.District;

import java.util.List;

public class DistrictService implements Services.DistrictService {
    private final DistrictDao districtDao = new DistrictDao();

    @Override
    public District getDistrict(int districtId) {
        return districtDao.getDistrict(districtId);
    }

    @Override
    public List<District> getByProvince(int provinceId) {
        return districtDao.getByProvince(provinceId);
    }
}
