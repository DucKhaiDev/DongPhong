package Services.deploy;

import Dao.deploy.ProvinceDao;
import Entity.Province;
import Services.IProvinceService;

import java.util.List;

public class ProvinceService implements IProvinceService {
    private final ProvinceDao provinceDao = new ProvinceDao();

    @Override
    public Province getProvince(int id) {
        return provinceDao.getProvince(id);
    }

    @Override
    public List<Province> getAll() {
        return provinceDao.getAll();
    }
}
