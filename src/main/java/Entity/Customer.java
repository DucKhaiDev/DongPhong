package Entity;

import java.io.Serializable;

public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    private String CUS_ID;
    private String CUS_FNAME;
    private String CUS_LNAME;
    private String CUS_EMAIL;
    private String CUS_ADDRESS;
    private String CUS_PHONE;

    public Customer() {}

    public Customer(String CUS_ID, String CUS_FNAME, String CUS_LNAME, String CUS_EMAIL, String CUS_ADDRESS, String CUS_PHONE) {
        this.CUS_ID = CUS_ID;
        this.CUS_FNAME = CUS_FNAME;
        this.CUS_LNAME = CUS_LNAME;
        this.CUS_EMAIL = CUS_EMAIL;
        this.CUS_ADDRESS = CUS_ADDRESS;
        this.CUS_PHONE = CUS_PHONE;
    }

    public String getCUS_ID() {
        return CUS_ID;
    }

    public void setCUS_ID(String CUS_ID) {
        this.CUS_ID = CUS_ID;
    }

    public String getCUS_FNAME() {
        return CUS_FNAME;
    }

    public void setCUS_FNAME(String CUS_FNAME) {
        this.CUS_FNAME = CUS_FNAME;
    }

    public String getCUS_LNAME() {
        return CUS_LNAME;
    }

    public void setCUS_LNAME(String CUS_LNAME) {
        this.CUS_LNAME = CUS_LNAME;
    }

    public String getCUS_EMAIL() {
        return CUS_EMAIL;
    }

    public void setCUS_EMAIL(String CUS_EMAIL) {
        this.CUS_EMAIL = CUS_EMAIL;
    }

    public String getCUS_ADDRESS() {
        return CUS_ADDRESS;
    }

    public void setCUS_ADDRESS(String CUS_ADDRESS) {
        this.CUS_ADDRESS = CUS_ADDRESS;
    }

    public String getCUS_PHONE() {
        return CUS_PHONE;
    }

    public void setCUS_PHONE(String CUS_PHONE) {
        this.CUS_PHONE = CUS_PHONE;
    }
}
