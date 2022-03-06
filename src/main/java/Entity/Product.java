package Entity;

import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    private String productId;
    private String productName;
    private double productRate;
    private String productDescription;
    private String productPrice;
    private String productCost;
    private int productQuantity;
    private Category category;
    private Brand brand;

    public Product() {}

    public Product(String productId, String productName, String productDescription, String productPrice, String productCost, int productQuantity, Category category, Brand brand) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productCost = productCost;
        this.productQuantity = productQuantity;
        this.category = category;
        this.brand = brand;
    }

    public Product(String productId, String productName, double productRate, String productDescription, String productPrice, String productCost, int productQuantity, Category category, Brand brand) {
        this.productId = productId;
        this.productName = productName;
        this.productRate = productRate;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productCost = productCost;
        this.productQuantity = productQuantity;
        this.category = category;
        this.brand = brand;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductRate() {
        return productRate;
    }

    public void setProductRate(double productRate) {
        this.productRate = productRate;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductCost() {
        return productCost;
    }

    public void setProductCost(String productCost) {
        this.productCost = productCost;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
