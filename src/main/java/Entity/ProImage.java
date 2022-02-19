package Entity;

import java.io.Serializable;

public class ProImage implements Serializable {
    private final static long serialVersionUID = 1L;

    private int IMG_ID;
    private String IMG_NAME;
    private Product PRO;

    public ProImage() {}

    public ProImage(int IMG_ID, String IMG_NAME, Product PRO) {
        this.IMG_ID = IMG_ID;
        this.IMG_NAME = IMG_NAME;
        this.PRO = PRO;
    }

    public int getIMG_ID() {
        return IMG_ID;
    }

    public void setIMG_ID(int IMG_ID) {
        this.IMG_ID = IMG_ID;
    }

    public String getIMG_NAME() {
        return IMG_NAME;
    }

    public void setIMG_NAME(String IMG_NAME) {
        this.IMG_NAME = IMG_NAME;
    }

    public Product getPRO() {
        return PRO;
    }

    public void setPRO(Product PRO) {
        this.PRO = PRO;
    }
}
