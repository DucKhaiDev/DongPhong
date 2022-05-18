package Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    private int orderId;
    private User user;
    private String recipientName;
    private String recipientAddress;
    private String recipientPhone;
    private Timestamp orderDate;
    private Date recipientDate;
    private byte orderStatus;
    private int orderSumProduct;
    private BigDecimal orderShipping;
    private BigDecimal orderTax;
    private BigDecimal orderSubTotal;
    private BigDecimal orderDiscount;
    private BigDecimal orderTotal;
    private Cart cart;
    private Payment payment;

    public Order(User user, String recipientName, String recipientAddress, String recipientPhone, Timestamp orderDate, Date recipientDate, byte orderStatus, int orderSumProduct, BigDecimal orderShipping, BigDecimal orderTax, BigDecimal orderSubTotal, BigDecimal orderDiscount, BigDecimal orderTotal, Cart cart, Payment payment) {
        this.user = user;
        this.recipientName = recipientName;
        this.recipientAddress = recipientAddress;
        this.recipientPhone = recipientPhone;
        this.orderDate = orderDate;
        this.recipientDate = recipientDate;
        this.orderStatus = orderStatus;
        this.orderSumProduct = orderSumProduct;
        this.orderShipping = orderShipping;
        this.orderTax = orderTax;
        this.orderSubTotal = orderSubTotal;
        this.orderDiscount = orderDiscount;
        this.orderTotal = orderTotal;
        this.cart = cart;
        this.payment = payment;
    }
}
