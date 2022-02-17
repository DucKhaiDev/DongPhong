package Entity;

import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    private String PRO_ID;
    private String PRO_NAME;
    private String PRO_IMAGE;
    private double PRO_RATE;
    private String PRO_DES;
    private double PRO_PRICE;
    private double PRO_COST;
    private String PRO_STATUS;
    private int PRO_QUANT;
    private String CAT_ID;
    private String BRA_ID;

    public Product() {}

    public Product(String PRO_ID, String PRO_NAME, String PRO_IMAGE, double PRO_RATE, String PRO_DES, double PRO_PRICE, double PRO_COST, String PRO_STATUS, int PRO_QUANT, String CAT_ID, String BRA_ID) {
        this.PRO_ID = PRO_ID;
        this.PRO_NAME = PRO_NAME;
        this.PRO_IMAGE = PRO_IMAGE;
        this.PRO_RATE = PRO_RATE;
        this.PRO_DES = PRO_DES;
        this.PRO_PRICE = PRO_PRICE;
        this.PRO_COST = PRO_COST;
        this.PRO_STATUS = PRO_STATUS;
        this.PRO_QUANT = PRO_QUANT;
        this.CAT_ID = CAT_ID;
        this.BRA_ID = BRA_ID;
    }

    public String getPRO_ID() {
        return PRO_ID;
    }

    public void setPRO_ID(String PRO_ID) {
        this.PRO_ID = PRO_ID;
    }

    public String getPRO_NAME() {
        return PRO_NAME;
    }

    public void setPRO_NAME(String PRO_NAME) {
        this.PRO_NAME = PRO_NAME;
    }

    public String getPRO_IMAGE() {
        return PRO_IMAGE;
    }

    public void setPRO_IMAGE(String PRO_IMAGE) {
        this.PRO_IMAGE = PRO_IMAGE;
    }

    public double getPRO_RATE() {
        return PRO_RATE;
    }

    public void setPRO_RATE(double PRO_RATE) {
        this.PRO_RATE = PRO_RATE;
    }

    public String getPRO_DES() {
        return PRO_DES;
    }

    public void setPRO_DES(String PRO_DES) {
        this.PRO_DES = PRO_DES;
    }

    public double getPRO_PRICE() {
        return PRO_PRICE;
    }

    public void setPRO_PRICE(double PRO_PRICE) {
        this.PRO_PRICE = PRO_PRICE;
    }

    public double getPRO_COST() {
        return PRO_COST;
    }

    public void setPRO_COST(double PRO_COST) {
        this.PRO_COST = PRO_COST;
    }

    public String getPRO_STATUS() {
        return PRO_STATUS;
    }

    public void setPRO_STATUS(String PRO_STATUS) {
        this.PRO_STATUS = PRO_STATUS;
    }

    public int getPRO_QUANT() {
        return PRO_QUANT;
    }

    public void setPRO_QUANT(int PRO_QUANT) {
        this.PRO_QUANT = PRO_QUANT;
    }

    public String getCAT_ID() {
        return CAT_ID;
    }

    public void setCAT_ID(String CAT_ID) {
        this.CAT_ID = CAT_ID;
    }

    public String getBRA_ID() {
        return BRA_ID;
    }

    public void setBRA_ID(String BRA_ID) {
        this.BRA_ID = BRA_ID;
    }
}
