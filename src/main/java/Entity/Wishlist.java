package Entity;

import java.io.Serializable;

public class Wishlist implements Serializable {
    private static final long serialVersionUID = 1L;

    private String WL_ID;
    private String CUS_ID;

    public Wishlist() {}

    public Wishlist(String WL_ID, String CUS_ID) {
        this.WL_ID = WL_ID;
        this.CUS_ID = CUS_ID;
    }

    public String getWL_ID() {
        return WL_ID;
    }

    public void setWL_ID(String WL_ID) {
        this.WL_ID = WL_ID;
    }

    public String getCUS_ID() {
        return CUS_ID;
    }

    public void setCUS_ID(String CUS_ID) {
        this.CUS_ID = CUS_ID;
    }
}
