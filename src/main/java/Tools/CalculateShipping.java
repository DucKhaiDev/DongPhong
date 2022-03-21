package Tools;

import Entity.Province;
import Services.deploy.ProvinceService;

import java.math.BigDecimal;

public class CalculateShipping {
    private final static ProvinceService provinceService = new ProvinceService();
    private final static Province BacNinh = provinceService.getProvince(27);

    public static BigDecimal calculateShipping(int id) {
        Province province = provinceService.getProvince(id);
        double lat1 = BacNinh.getLatitude();
        double long1 = BacNinh.getLongitude();
        double lat2 = province.getLatitude();
        double long2 = province.getLongitude();
        double distance = GraphHopper.calculateDistance(lat1, long1, lat2, long2);

        return (new BigDecimal(distance/1000)).multiply(new BigDecimal("50000"));
    }
}
