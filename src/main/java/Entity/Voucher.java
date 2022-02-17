package Entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Voucher implements Serializable {
    private static final long serialVersionUID = 1L;

    private String VOUCHER;
    private int MIN_PRO;
    private double MIN_VAL;
    private Timestamp FROM_DATE;
    private Timestamp TO_DATE;

    public Voucher() {}

    public Voucher(String VOUCHER, int MIN_PRO, double MIN_VAL, Timestamp FROM_DATE, Timestamp TO_DATE) {
        this.VOUCHER = VOUCHER;
        this.MIN_PRO = MIN_PRO;
        this.MIN_VAL = MIN_VAL;
        this.FROM_DATE = FROM_DATE;
        this.TO_DATE = TO_DATE;
    }

    public String getVOUCHER() {
        return VOUCHER;
    }

    public void setVOUCHER(String VOUCHER) {
        this.VOUCHER = VOUCHER;
    }

    public int getMIN_PRO() {
        return MIN_PRO;
    }

    public void setMIN_PRO(int MIN_PRO) {
        this.MIN_PRO = MIN_PRO;
    }

    public double getMIN_VAL() {
        return MIN_VAL;
    }

    public void setMIN_VAL(double MIN_VAL) {
        this.MIN_VAL = MIN_VAL;
    }

    public Timestamp getFROM_DATE() {
        return FROM_DATE;
    }

    public void setFROM_DATE(Timestamp FROM_DATE) {
        this.FROM_DATE = FROM_DATE;
    }

    public Timestamp getTO_DATE() {
        return TO_DATE;
    }

    public void setTO_DATE(Timestamp TO_DATE) {
        this.TO_DATE = TO_DATE;
    }
}
