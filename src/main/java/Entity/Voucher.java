package Entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Voucher implements Serializable {
    private static final long serialVersionUID = 1L;

    private String voucher;
    private int minProduct;
    private double minValue;
    private Timestamp fromDate;
    private Timestamp toDate;

    public Voucher() {}

    public Voucher(String voucher, int minProduct, double minValue, Timestamp fromDate, Timestamp toDate) {
        this.voucher = voucher;
        this.minProduct = minProduct;
        this.minValue = minValue;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    public int getMinProduct() {
        return minProduct;
    }

    public void setMinProduct(int minProduct) {
        this.minProduct = minProduct;
    }

    public double getMinValue() {
        return minValue;
    }

    public void setMinValue(double minValue) {
        this.minValue = minValue;
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
