package Entity;

import java.io.Serializable;

public class Brand implements Serializable {
    private static final long serialVersionUID = 1L;

    private String brandId;
    private String brandName;
    private String brandDescription;

    public Brand() {}

    public Brand(String brandId, String brandName, String brandDescription) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.brandDescription = brandDescription;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandDescription() {
        return brandDescription;
    }

    public void setBrandDescription(String brandDescription) {
        this.brandDescription = brandDescription;
    }
}
