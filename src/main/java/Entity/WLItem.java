package Entity;

import java.io.Serializable;

public class WLItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private String WLITEM_ID;
    private String PRO_ID;
    private String WL_ID;

    public WLItem() {}

    public WLItem(String WLITEM_ID, String PRO_ID, String WL_ID) {
        this.WLITEM_ID = WLITEM_ID;
        this.PRO_ID = PRO_ID;
        this.WL_ID = WL_ID;
    }

    public String getWLITEM_ID() {
        return WLITEM_ID;
    }

    public void setWLITEM_ID(String WLITEM_ID) {
        this.WLITEM_ID = WLITEM_ID;
    }

    public String getPRO_ID() {
        return PRO_ID;
    }

    public void setPRO_ID(String PRO_ID) {
        this.PRO_ID = PRO_ID;
    }

    public String getWL_ID() {
        return WL_ID;
    }

    public void setWL_ID(String WL_ID) {
        this.WL_ID = WL_ID;
    }
}
