package Services;

import Entity.Province;

import java.util.List;

public interface IProvinceService {
    Province getProvince(int id);
    List<Province> getAll();
}
