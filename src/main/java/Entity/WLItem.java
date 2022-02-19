package Entity;

import java.io.Serializable;

public class WLItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private String WLITEM_ID;
    private Product PRO;
    private Wishlist WL;

    public WLItem() {}

    public WLItem(String WLITEM_ID, Product PRO, Wishlist WL) {
        this.WLITEM_ID = WLITEM_ID;
        this.PRO = PRO;
        this.WL = WL;
    }

    public String getWLITEM_ID() {
        return WLITEM_ID;
    }

    public void setWLITEM_ID(String WLITEM_ID) {
        this.WLITEM_ID = WLITEM_ID;
    }

    public Product getPRO() {
        return PRO;
    }

    public void setPRO(Product PRO) {
        this.PRO = PRO;
    }

    public Wishlist getWL() {
        return WL;
    }

    public void setWL(Wishlist WL) {
        this.WL = WL;
    }
}
