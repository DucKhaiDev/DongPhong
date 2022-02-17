package Entity;

import java.io.Serializable;

public class CartItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private String CITEM_ID;
    private int QUANT;
    private double VALUE;
    private String PRO_ID;
    private String CART_ID;

    public CartItem() {}

    public CartItem(String CITEM_ID, int QUANT, double VALUE, String PRO_ID, String CART_ID) {
        this.CITEM_ID = CITEM_ID;
        this.QUANT = QUANT;
        this.VALUE = VALUE;
        this.PRO_ID = PRO_ID;
        this.CART_ID = CART_ID;
    }

    public String getCITEM_ID() {
        return CITEM_ID;
    }

    public void setCITEM_ID(String CITEM_ID) {
        this.CITEM_ID = CITEM_ID;
    }

    public int getQUANT() {
        return QUANT;
    }

    public void setQUANT(int QUANT) {
        this.QUANT = QUANT;
    }

    public double getVALUE() {
        return VALUE;
    }

    public void setVALUE(double VALUE) {
        this.VALUE = VALUE;
    }

    public String getPRO_ID() {
        return PRO_ID;
    }

    public void setPRO_ID(String PRO_ID) {
        this.PRO_ID = PRO_ID;
    }

    public String getCART_ID() {
        return CART_ID;
    }

    public void setCART_ID(String CART_ID) {
        this.CART_ID = CART_ID;
    }
}
