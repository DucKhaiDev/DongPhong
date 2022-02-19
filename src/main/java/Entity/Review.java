package Entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Review implements Serializable {
    private static final long serialVersionUID = 1L;

    private int REV_ID;
    private Customer CUS;
    private Product PRO;
    private String REV_CONTENT;
    private Timestamp REV_DATE;
    private String REV_IMG;

    public Review() {}

    public Review(int REV_ID, Customer CUS, Product PRO, String REV_CONTENT, Timestamp REV_DATE, String REV_IMG) {
        this.REV_ID = REV_ID;
        this.CUS = CUS;
        this.PRO = PRO;
        this.REV_CONTENT = REV_CONTENT;
        this.REV_DATE = REV_DATE;
        this.REV_IMG = REV_IMG;
    }

    public int getREV_ID() {
        return REV_ID;
    }

    public void setREV_ID(int REV_ID) {
        this.REV_ID = REV_ID;
    }

    public Customer getCUS() {
        return CUS;
    }

    public void setCUS(Customer CUS) {
        this.CUS = CUS;
    }

    public Product getPRO() {
        return PRO;
    }

    public void setPRO(Product PRO) {
        this.PRO = PRO;
    }

    public String getREV_CONTENT() {
        return REV_CONTENT;
    }

    public void setREV_CONTENT(String REV_CONTENT) {
        this.REV_CONTENT = REV_CONTENT;
    }

    public Timestamp getREV_DATE() {
        return REV_DATE;
    }

    public void setREV_DATE(Timestamp REV_DATE) {
        this.REV_DATE = REV_DATE;
    }

    public String getREV_IMG() {
        return REV_IMG;
    }

    public void setREV_IMG(String REV_IMG) {
        this.REV_IMG = REV_IMG;
    }
}
