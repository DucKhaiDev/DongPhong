package Entity;

import java.io.Serializable;

public class CartItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private String CITEM_ID;
    private int QUANT;
    private double VALUE;
    private Product PRO;
    private Cart CAT;

    public CartItem() {}

    public CartItem(String CITEM_ID, int QUANT, double VALUE, Product PRO, Cart CAT) {
        this.CITEM_ID = CITEM_ID;
        this.QUANT = QUANT;
        this.VALUE = VALUE;
        this.PRO = PRO;
        this.CAT = CAT;
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

    public Product getPRO() {
        return PRO;
    }

    public void setPRO(Product PRO) {
        this.PRO = PRO;
    }

    public Cart getCAT() {
        return CAT;
    }

    public void setCAT(Cart CAT) {
        this.CAT = CAT;
    }
}
