package Entity;

public class District {
    private int districtId;
    private String districtName;
    private Province province;

    public District() {}

    public District(int districtId, String districtName, Province province) {
        this.districtId = districtId;
        this.districtName = districtName;
        this.province = province;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }
}
