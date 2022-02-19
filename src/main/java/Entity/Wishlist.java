package Entity;

import java.io.Serializable;

public class Wishlist implements Serializable {
    private static final long serialVersionUID = 1L;

    private String WL_ID;
    private Customer CUS;

    public Wishlist() {}

    public Wishlist(String WL_ID, Customer CUS) {
        this.WL_ID = WL_ID;
        this.CUS = CUS;
    }

    public String getWL_ID() {
        return WL_ID;
    }

    public void setWL_ID(String WL_ID) {
        this.WL_ID = WL_ID;
    }

    public Customer getCUS() {
        return CUS;
    }

    public void setCUS(Customer CUS) {
        this.CUS = CUS;
    }
}
