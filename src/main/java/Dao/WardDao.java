package Dao;

import Entity.Ward;

import java.util.List;

public interface WardDao {
    List<Ward> getByDistrict(int districtId);
}
