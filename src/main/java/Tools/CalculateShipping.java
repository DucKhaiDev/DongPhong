package Tools;

import Entity.Province;
import Util.Constant;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculateShipping {
    private final static Province BacNinh = Constant.Service.PROVINCE_SERVICE.getProvince(27);

    public static BigDecimal calculateShipping(int id) {
        Province province = Constant.Service.PROVINCE_SERVICE.getProvince(id);
        double lat1 = BacNinh.getLatitude();
        double long1 = BacNinh.getLongitude();
        double lat2 = province.getLatitude();
        double long2 = province.getLongitude();
        double distance = GraphHopper.calculateDistance(lat1, long1, lat2, long2);

        return (new BigDecimal(distance).divide(new BigDecimal(1000), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("50000")));
    }
}