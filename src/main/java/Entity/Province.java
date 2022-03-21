package Entity;

public class Province {
    private int provinceId;
    private String provinceName;
    private double latitude;
    private double longitude;

    public Province() {}

    public Province(int provinceId, String provinceName, double latitude, double longitude) {
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
