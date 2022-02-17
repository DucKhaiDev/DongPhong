package Entity;

import java.io.Serializable;

public class Payment implements Serializable {
    private static final long serialVersionUID = 1L;

    private String PAY_ID;
    private String PAY_METHOD;

    public Payment() {}

    public Payment(String PAY_ID, String PAY_METHOD) {
        this.PAY_ID = PAY_ID;
        this.PAY_METHOD = PAY_METHOD;
    }

    public String getPAY_ID() {
        return PAY_ID;
    }

    public void setPAY_ID(String PAY_ID) {
        this.PAY_ID = PAY_ID;
    }

    public String getPAY_METHOD() {
        return PAY_METHOD;
    }

    public void setPAY_METHOD(String PAY_METHOD) {
        this.PAY_METHOD = PAY_METHOD;
    }
}
