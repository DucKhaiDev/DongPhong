package Entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    private String orderId;
    private User user;
    private String recipientName;
    private String recipientAddress;
    private String recipientPhone;
    private Timestamp orderDate;
    private Date recipientDate;
    private String orderStatus;
    private int orderTotalProduct;
    private String orderTotalPayment;
    private Cart cart;
    private Payment payment;

    public Order() {}

    public Order(String orderId, User user, String recipientName, String recipientAddress, String recipientPhone, Timestamp orderDate, Date recipientDate, String orderStatus, int orderTotalProduct, String orderTotalPayment, Cart cart, Payment payment) {
        this.orderId = orderId;
        this.user = user;
        this.recipientName = recipientName;
        this.recipientAddress = recipientAddress;
        this.recipientPhone = recipientPhone;
        this.orderDate = orderDate;
        this.recipientDate = recipientDate;
        this.orderStatus = orderStatus;
        this.orderTotalProduct = orderTotalProduct;
        this.orderTotalPayment = orderTotalPayment;
        this.cart = cart;
        this.payment = payment;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getOrderTotalProduct() {
        return orderTotalProduct;
    }

    public void setOrderTotalProduct(int orderTotalProduct) {
        this.orderTotalProduct = orderTotalProduct;
    }

    public String getOrderTotalPayment() {
        return orderTotalPayment;
    }

    public void setOrderTotalPayment(String orderTotalPayment) {
        this.orderTotalPayment = orderTotalPayment;
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
