package Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class Voucher implements Serializable {
    private static final long serialVersionUID = 1L;

    private String voucher;
    private int minProduct;
    private BigDecimal minValue;
    private Timestamp fromDate;
    private Timestamp toDate;

    public Voucher() {}

    public Voucher(String voucher, int minProduct, BigDecimal minValue, Timestamp fromDate, Timestamp toDate) {
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

    public BigDecimal getMinValue() {
        return minValue;
    }

    public void setMinValue(BigDecimal minValue) {
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
