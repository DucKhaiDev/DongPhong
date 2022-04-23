package Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    private int orderId;
    private User user;
    private String recipientName;
    private String recipientAddress;
    private String recipientPhone;
    private Timestamp orderDate;
    private Date recipientDate;
    private Boolean orderStatus;
    private int orderSumProduct;
    private BigDecimal orderShipping;
    private BigDecimal orderTax;
    private BigDecimal orderSubTotal;
    private BigDecimal orderDiscount;
    private BigDecimal orderTotal;
    private Cart cart;
    private Payment payment;

    public Order() {}

    public Order(User user, String recipientName, String recipientAddress, String recipientPhone, Timestamp orderDate, Date recipientDate, Boolean orderStatus, int orderSumProduct, BigDecimal orderShipping, BigDecimal orderTax, BigDecimal orderSubTotal, BigDecimal orderDiscount, BigDecimal orderTotal, Cart cart, Payment payment) {
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

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public String getRecipientPhone() {
        return recipientPhone;
    }

    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public Date getRecipientDate() {
        return recipientDate;
    }

    public void setRecipientDate(Date recipientDate) {
        this.recipientDate = recipientDate;
    }

    public Boolean getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Boolean orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getOrderSumProduct() {
        return orderSumProduct;
    }

    public void setOrderSumProduct(int orderSumProduct) {
        this.orderSumProduct = orderSumProduct;
    }

    public BigDecimal getOrderSubTotal() {
        return orderSubTotal;
    }

    public BigDecimal getOrderShipping() {
        return orderShipping;
    }

    public void setOrderShipping(BigDecimal orderShipping) {
        this.orderShipping = orderShipping;
    }

    public BigDecimal getOrderTax() {
        return orderTax;
    }

    public void setOrderTax(BigDecimal orderTax) {
        this.orderTax = orderTax;
    }

    public void setOrderSubTotal(BigDecimal orderSubTotal) {
        this.orderSubTotal = orderSubTotal;
    }

    public BigDecimal getOrderDiscount() {
        return orderDiscount;
    }

    public void setOrderDiscount(BigDecimal orderDiscount) {
        this.orderDiscount = orderDiscount;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
