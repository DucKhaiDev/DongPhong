package Entity;

import java.io.Serializable;

public class WLItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private String wlItemId;
    private Product product;
    private WishList wishList;

    public WLItem() {}

    public WLItem(String wlItemId, Product product, WishList wishList) {
        this.wlItemId = wlItemId;
        this.product = product;
        this.wishList = wishList;
    }

    public String getWlItemId() {
        return wlItemId;
    }

    public void setWlItemId(String wlItemId) {
        this.wlItemId = wlItemId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public WishList getWishList() {
        return wishList;
    }

    public void setWishList(WishList wishList) {
        this.wishList = wishList;
    }
}
