package Entity;

import java.io.Serializable;

public class Cart implements Serializable {
    private static final long serialVersionUID = 1L;

    private String CART_ID;
    private String CUS_ID;

    public Cart() {}

    public Cart(String CART_ID, String CUS_ID) {
        this.CART_ID = CART_ID;
        this.CUS_ID = CUS_ID;
    }

    public String getCART_ID() {
        return CART_ID;
    }

    public void setCART_ID(String CART_ID) {
        this.CART_ID = CART_ID;
    }

    public String getCUS_ID() {
        return CUS_ID;
    }

    public void setCUS_ID(String CUS_ID) {
        this.CUS_ID = CUS_ID;
    }
}
