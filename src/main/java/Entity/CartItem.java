package Entity;

import java.io.Serializable;

public class CartItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private String CITEM_ID;
    private int QUANT;
    private String VALUE;
    private Product PRO;
    private Cart CART;

    public CartItem() {}

    public CartItem(String CITEM_ID, int QUANT, String VALUE, Product PRO, Cart CART) {
        this.CITEM_ID = CITEM_ID;
        this.QUANT = QUANT;
        this.VALUE = VALUE;
        this.PRO = PRO;
        this.CART = CART;
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

    public String getVALUE() {
        return VALUE;
    }

    public void setVALUE(String VALUE) {
        this.VALUE = VALUE;
    }

    public Product getPRO() {
        return PRO;
    }

    public void setPRO(Product PRO) {
        this.PRO = PRO;
    }

    public Cart getCART() {
        return CART;
    }

    public void setCART(Cart CART) {
        this.CART = CART;
    }
}
