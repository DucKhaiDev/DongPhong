package Entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private int cartItemId;
    private int quantity;
    private BigDecimal value;
    private Product product;
    private Cart cart;

    public CartItem() {}

    public CartItem(int quantity, BigDecimal value, Product product, Cart cart) {
        this.quantity = quantity;
        this.value = value;
        this.product = product;
        this.cart = cart;
    }

    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
