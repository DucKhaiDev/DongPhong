package Entity;

import Services.deploy.ProImageService;
import Services.deploy.ProductService;

import java.io.Serializable;
import java.util.List;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    ProImageService imageService = new ProImageService();

    private String PRO_ID;
    private String PRO_NAME;
    private double PRO_RATE;
    private String PRO_DES;
    private double PRO_PRICE;
    private double PRO_COST;
    private int PRO_QUANT;
    private Category CAT;
    private Brand BRA;

    public Product() {}

    public Product(String PRO_ID, String PRO_NAME, Category CAT, Brand BRA) {
        this.PRO_ID = PRO_ID;
        this.PRO_NAME = PRO_NAME;
        this.CAT = CAT;
        this.BRA = BRA;
    }

    public Product(String PRO_ID, String PRO_NAME, double PRO_RATE, String PRO_DES, double PRO_PRICE, double PRO_COST, int PRO_QUANT, Category CAT, Brand BRA) {
        this.PRO_ID = PRO_ID;
        this.PRO_NAME = PRO_NAME;
        this.PRO_RATE = PRO_RATE;
        this.PRO_DES = PRO_DES;
        this.PRO_PRICE = PRO_PRICE;
        this.PRO_COST = PRO_COST;
        this.PRO_QUANT = PRO_QUANT;
        this.CAT = CAT;
        this.BRA = BRA;
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

    public int getPRO_QUANT() {
        return PRO_QUANT;
    }

    public void setPRO_QUANT(int PRO_QUANT) {
        this.PRO_QUANT = PRO_QUANT;
    }

    public Category getCAT() {
        return CAT;
    }

    public void setCAT(Category CAT) {
        this.CAT = CAT;
    }

    public Brand getBRA() {
        return BRA;
    }

    public void setBRA(Brand BRA) {
        this.BRA = BRA;
    }

    public String getProReIMG() {
        List<String> proImages = imageService.getProImage(PRO_ID);
        if (!proImages.isEmpty()) {
            return proImages.get(0);
        }

        return null;
    }

    public List<String> getProImage() {
        return imageService.getProImage(PRO_ID);
    }
}
