package Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
}