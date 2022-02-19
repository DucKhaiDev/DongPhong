package Entity;

import java.io.Serializable;

public class Cart implements Serializable {
    private static final long serialVersionUID = 1L;

    private String CART_ID;
    private Customer CUS;

    public Cart() {}

    public Cart(String CART_ID, Customer CUS) {
        this.CART_ID = CART_ID;
        this.CUS = CUS;
    }

    public String getCART_ID() {
        return CART_ID;
    }

    public void setCART_ID(String CART_ID) {
        this.CART_ID = CART_ID;
    }

    public Customer getCUS() {
        return CUS;
    }

    public void setCUS(Customer CUS) {
        this.CUS = CUS;
    }
}
