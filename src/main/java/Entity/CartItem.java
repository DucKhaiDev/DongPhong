package Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class CartItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private int cartItemId;
    private int quantity;
    private BigDecimal value;
    private Product product;
    private Cart cart;

    public CartItem(int quantity, BigDecimal value, Product product, Cart cart) {
        this.quantity = quantity;
        this.value = value;
        this.product = product;
        this.cart = cart;
    }
}