package Dao;

import Entity.District;

import java.util.List;

public interface DistrictDao {
    District getDistrict(int districtId);
    List<District> getByProvince(int provinceId);
}
