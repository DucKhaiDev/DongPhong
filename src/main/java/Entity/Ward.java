package Entity;

public class Ward {
    private int wardId;
    private String wardName;
    private District district;

    public Ward() {}

    public Ward(int wardId, String wardName, District district) {
        this.wardId = wardId;
        this.wardName = wardName;
        this.district = district;
    }

    public int getWardId() {
        return wardId;
    }

    public void setWardId(int wardId) {
        this.wardId = wardId;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }
}
