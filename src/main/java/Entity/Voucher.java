package Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class Voucher implements Serializable {
    private static final long serialVersionUID = 1L;

    private String voucherId;
    private int minProduct;
    private BigDecimal minValue;
    private double discount;
    private BigDecimal discountMax;
    private int quantity;
    private Timestamp fromDate;
    private Timestamp toDate;

    public Voucher() {
    }

    public Voucher(String voucherId, int minProduct, BigDecimal minValue, double discount, BigDecimal discountMax, int quantity, Timestamp fromDate, Timestamp toDate) {
        this.voucherId = voucherId;
        this.minProduct = minProduct;
        this.minValue = minValue;
        this.discount = discount;
        this.discountMax = discountMax;
        this.quantity = quantity;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(String voucherId) {
        this.voucherId = voucherId;
    }

    public int getMinProduct() {
        return minProduct;
    }

    public void setMinProduct(int minProduct) {
        this.minProduct = minProduct;
    }

    public BigDecimal getMinValue() {
        return minValue;
    }

    public void setMinValue(BigDecimal minValue) {
        this.minValue = minValue;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public BigDecimal getDiscountMax() {
        return discountMax;
    }

    public void setDiscountMax(BigDecimal discountMax) {
        this.discountMax = discountMax;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Timestamp getFromDate() {
        return fromDate;
    }

    public void setFromDate(Timestamp fromDate) {
        this.fromDate = fromDate;
    }

    public Timestamp getToDate() {
        return toDate;
    }

    public void setToDate(Timestamp toDate) {
        this.toDate = toDate;
    }
}
