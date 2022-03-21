package Services;

import Entity.District;

import java.util.List;

public interface DistrictService {
    District getDistrict(int districtId);
    List<District> getByProvince(int provinceId);
}
