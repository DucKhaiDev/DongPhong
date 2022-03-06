package Entity;

import java.io.Serializable;

public class WishList implements Serializable {
    private static final long serialVersionUID = 1L;

    private String wishListId;
    private User user;

    public WishList() {}

    public WishList(String wishListId, User user) {
        this.wishListId = wishListId;
        this.user = user;
    }

    public String getWishListId() {
        return wishListId;
    }

    public void setWishListId(String wishListId) {
        this.wishListId = wishListId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
