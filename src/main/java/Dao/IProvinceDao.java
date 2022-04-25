package Dao;

import Entity.Province;

import java.util.List;

public interface IProvinceDao {
    Province getProvince(int id);

    List<Province> getAll();
}
