package Entity;

import java.io.Serializable;

public class User implements Serializable {
    private int USER_ID;
    private String FIRSTNAME;
    private String LASTNAME;
    private String USERNAME;
    private String PASSWORD;
    private String EMAIL;
    private String ADDRESS;
    private String PHONE;
    private String AVATAR;
    private boolean ROLE_ID;

    public User() {}

    public User(String USERNAME, String PASSWORD, String EMAIL) {
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
        this.EMAIL = EMAIL;
    }

    public User(String FIRSTNAME, String LASTNAME, String USERNAME, String PASSWORD, String EMAIL, String ADDRESS, String PHONE, String AVATAR, boolean ROLE_ID) {
        this.FIRSTNAME = FIRSTNAME;
        this.LASTNAME = LASTNAME;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
        this.EMAIL = EMAIL;
        this.ADDRESS = ADDRESS;
        this.PHONE = PHONE;
        this.AVATAR = AVATAR;
        this.ROLE_ID = ROLE_ID;
    }

    public int getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(int USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getFIRSTNAME() {
        return FIRSTNAME;
    }

    public void setFIRSTNAME(String FIRSTNAME) {
        this.FIRSTNAME = FIRSTNAME;
    }

    public String getLASTNAME() {
        return LASTNAME;
    }

    public void setLASTNAME(String LASTNAME) {
        this.LASTNAME = LASTNAME;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    public String getAVATAR() {
        return AVATAR;
    }

    public void setAVATAR(String AVATAR) {
        this.AVATAR = AVATAR;
    }

    public boolean getROLE_ID() {
        return ROLE_ID;
    }

    public void setROLE_ID(boolean ROLE_ID) {
        this.ROLE_ID = ROLE_ID;
    }
}
