package Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class WLItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private int wlItemId;
    private Product product;
    private WishList wishList;

    public WLItem(Product product, WishList wishList) {
        this.product = product;
        this.wishList = wishList;
    }
}