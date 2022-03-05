package Entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    private String ORD_ID;
    private Customer CUS;
    private String REC_NAME;
    private String REC_ADDRESS;
    private String REC_PHONE;
    private Timestamp ORD_DATE;
    private Date REC_DATE;
    private String ORD_STATUS;
    private int ORD_TOTALPRO;
    private String ORD_TOTALPAY;
    private Cart CART;
    private Payment PAY;

    public Order() {}

    public Order(String ORD_ID, Customer CUS, String REC_NAME, String REC_ADDRESS, String REC_PHONE, Timestamp ORD_DATE, Date REC_DATE, String ORD_STATUS, int ORD_TOTALPRO, String ORD_TOTALPAY, Cart CART, Payment PAY) {
        this.ORD_ID = ORD_ID;
        this.CUS = CUS;
        this.REC_NAME = REC_NAME;
        this.REC_ADDRESS = REC_ADDRESS;
        this.REC_PHONE = REC_PHONE;
        this.ORD_DATE = ORD_DATE;
        this.REC_DATE = REC_DATE;
        this.ORD_STATUS = ORD_STATUS;
        this.ORD_TOTALPRO = ORD_TOTALPRO;
        this.ORD_TOTALPAY = ORD_TOTALPAY;
        this.CART = CART;
        this.PAY = PAY;
    }

    public String getORD_ID() {
        return ORD_ID;
    }

    public void setORD_ID(String ORD_ID) {
        this.ORD_ID = ORD_ID;
    }

    public Customer getCUS() {
        return CUS;
    }

    public void setCUS(Customer CUS) {
        this.CUS = CUS;
    }

    public String getREC_NAME() {
        return REC_NAME;
    }

    public void setREC_NAME(String REC_NAME) {
        this.REC_NAME = REC_NAME;
    }

    public String getREC_ADDRESS() {
        return REC_ADDRESS;
    }

    public void setREC_ADDRESS(String REC_ADDRESS) {
        this.REC_ADDRESS = REC_ADDRESS;
    }

    public String getREC_PHONE() {
        return REC_PHONE;
    }

    public void setREC_PHONE(String REC_PHONE) {
        this.REC_PHONE = REC_PHONE;
    }

    public Timestamp getORD_DATE() {
        return ORD_DATE;
    }

    public void setORD_DATE(Timestamp ORD_DATE) {
        this.ORD_DATE = ORD_DATE;
    }

    public Date getREC_DATE() {
        return REC_DATE;
    }

    public void setREC_DATE(Date REC_DATE) {
        this.REC_DATE = REC_DATE;
    }

    public String getORD_STATUS() {
        return ORD_STATUS;
    }

    public void setORD_STATUS(String ORD_STATUS) {
        this.ORD_STATUS = ORD_STATUS;
    }

    public int getORD_TOTALPRO() {
        return ORD_TOTALPRO;
    }

    public void setORD_TOTALPRO(int ORD_TOTALPRO) {
        this.ORD_TOTALPRO = ORD_TOTALPRO;
    }

    public String getORD_TOTALPAY() {
        return ORD_TOTALPAY;
    }

    public void setORD_TOTALPAY(String ORD_TOTALPAY) {
        this.ORD_TOTALPAY = ORD_TOTALPAY;
    }

    public Cart getCART() {
        return CART;
    }

    public void setCART(Cart CART) {
        this.CART = CART;
    }

    public Payment getPAY() {
        return PAY;
    }

    public void setPAY(Payment PAY) {
        this.PAY = PAY;
    }
}
