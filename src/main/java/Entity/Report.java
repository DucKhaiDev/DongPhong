package Entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Report implements Serializable {
    private static final long serialVersionUID = 1L;

    private int monthDate;
    private int yearDate;
    private int countId;
    private BigDecimal sumSubTotal;
    private BigDecimal sumDiscount;
    private BigDecimal sumTax;
    private BigDecimal sumShipping;
    private BigDecimal sumTotal;

    public Report(){
    }

    public Report(int monthDate, int yearDate, int countId, BigDecimal sumSubTotal, BigDecimal sumDiscount, BigDecimal sumTax, BigDecimal sumShipping, BigDecimal sumTotal) {
        this.monthDate = monthDate;
        this.yearDate = yearDate;
        this.countId = countId;
        this.sumSubTotal = sumSubTotal;
        this.sumDiscount = sumDiscount;
        this.sumTax = sumTax;
        this.sumShipping = sumShipping;
        this.sumTotal = sumTotal;
    }

    public int getMonthDate() {
        return monthDate;
    }

    public void setMonthDate(int monthDate) {
        this.monthDate = monthDate;
    }

    public int getYearDate() {
        return yearDate;
    }

    public void setYearDate(int yearDate) {
        this.yearDate = yearDate;
    }

    public int getCountId() {
        return countId;
    }

    public void setCountId(int countId) {
        this.countId = countId;
    }

    public BigDecimal getSumSubTotal() {
        return sumSubTotal;
    }

    public void setSumSubTotal(BigDecimal sumSubTotal) {
        this.sumSubTotal = sumSubTotal;
    }

    public BigDecimal getSumDiscount() {
        return sumDiscount;
    }

    public void setSumDiscount(BigDecimal sumDiscount) {
        this.sumDiscount = sumDiscount;
    }

    public BigDecimal getSumTax() {
        return sumTax;
    }

    public void setSumTax(BigDecimal sumTax) {
        this.sumTax = sumTax;
    }

    public BigDecimal getSumShipping() {
        return sumShipping;
    }

    public void setSumShipping(BigDecimal sumShipping) {
        this.sumShipping = sumShipping;
    }

    public BigDecimal getSumTotal() {
        return sumTotal;
    }

    public void setSumTotal(BigDecimal sumTotal) {
        this.sumTotal = sumTotal;
    }
}
