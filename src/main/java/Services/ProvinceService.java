package Services;

import Entity.Province;

import java.util.List;

public interface ProvinceService {
    Province getProvince(int id);
    List<Province> getAll();
}
