package Entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    private String productId;
    private String productName;
    private double productRate;
    private String productDescription;
    private String productDimension;
    private String productWeight;
    private String productMaterial;
    private String productColor;
    private BigDecimal productPrice;
    private BigDecimal productCost;
    private int productQuantity;
    private Category category;
    private Brand brand;

    public Product() {
    }

    public Product(String productId, String productName, String productDescription, String productDimension, String productWeight, String productMaterial, String productColor, BigDecimal productPrice, BigDecimal productCost, int productQuantity, Category category, Brand brand) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productDimension = productDimension;
        this.productWeight = productWeight;
        this.productMaterial = productMaterial;
        this.productColor = productColor;
        this.productPrice = productPrice;
        this.productCost = productCost;
        this.productQuantity = productQuantity;
        this.category = category;
        this.brand = brand;
    }

    public Product(String productId, String productName, double productRate, String productDescription, BigDecimal productPrice, BigDecimal productCost, int productQuantity, Category category, Brand brand) {
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

    public String getProductDimension() {
        return productDimension;
    }

    public void setProductDimension(String productDimension) {
        this.productDimension = productDimension;
    }

    public String getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(String productWeight) {
        this.productWeight = productWeight;
    }

    public String getProductMaterial() {
        return productMaterial;
    }

    public void setProductMaterial(String productMaterial) {
        this.productMaterial = productMaterial;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public BigDecimal getProductCost() {
        return productCost;
    }

    public void setProductCost(BigDecimal productCost) {
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
