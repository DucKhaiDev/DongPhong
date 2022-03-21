package Dao;

import Entity.Province;

import java.util.List;

public interface ProvinceDao {
    Province getProvince(int id);
    List<Province> getAll();
}
