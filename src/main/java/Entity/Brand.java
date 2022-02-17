package Entity;

import java.io.Serializable;

public class Brand implements Serializable {
    private static final long serialVersionUID = 1L;

    private String BRA_ID;
    private String BRA_NAME;
    private String BRA_DES;

    public Brand() {}

    public Brand(String BRA_ID, String BRA_NAME, String BRA_DES) {
        this.BRA_ID = BRA_ID;
        this.BRA_NAME = BRA_NAME;
        this.BRA_DES = BRA_DES;
    }

    public String getBRA_ID() {
        return BRA_ID;
    }

    public void setBRA_ID(String BRA_ID) {
        this.BRA_ID = BRA_ID;
    }

    public String getBRA_NAME() {
        return BRA_NAME;
    }

    public void setBRA_NAME(String BRA_NAME) {
        this.BRA_NAME = BRA_NAME;
    }

    public String getBRA_DES() {
        return BRA_DES;
    }

    public void setBRA_DES(String BRA_DES) {
        this.BRA_DES = BRA_DES;
    }
}
