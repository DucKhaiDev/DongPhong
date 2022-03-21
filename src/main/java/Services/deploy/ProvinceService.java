package Services.deploy;

import Dao.deploy.ProvinceDao;
import Entity.Province;

import java.util.List;

public class ProvinceService implements Services.ProvinceService {
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
