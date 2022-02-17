package Entity;

import java.io.Serializable;

public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    private String CAT_ID;
    private String CAT_NAME;
    private String CAT_DES;

    public Category() {}

    public Category(String CAT_ID, String CAT_NAME, String CAT_DES) {
        this.CAT_ID = CAT_ID;
        this.CAT_NAME = CAT_NAME;
        this.CAT_DES = CAT_DES;
    }

    public String getCAT_ID() {
        return CAT_ID;
    }

    public void setCAT_ID(String CAT_ID) {
        this.CAT_ID = CAT_ID;
    }

    public String getCAT_NAME() {
        return CAT_NAME;
    }

    public void setCAT_NAME(String CAT_NAME) {
        this.CAT_NAME = CAT_NAME;
    }

    public String getCAT_DES() {
        return CAT_DES;
    }

    public void setCAT_DES(String CAT_DES) {
        this.CAT_DES = CAT_DES;
    }
}
