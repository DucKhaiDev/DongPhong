package Entity;

import java.io.Serializable;

public class WLItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private int wlItemId;
    private Product product;
    private WishList wishList;

    public WLItem() {
    }

    public WLItem(Product product, WishList wishList) {
        this.product = product;
        this.wishList = wishList;
    }

    public WLItem(int wlItemId, Product product, WishList wishList) {
        this.wlItemId = wlItemId;
        this.product = product;
        this.wishList = wishList;
    }

    public int getWlItemId() {
        return wlItemId;
    }

    public void setWlItemId(int wlItemId) {
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
