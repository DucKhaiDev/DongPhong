package Dao.deploy;

import Connect.DBConnect;
import Dao.IOrderDao;
import Entity.Order;
import Services.deploy.CartService;
import Services.deploy.PaymentService;
import Services.deploy.UserService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao implements IOrderDao {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    private final UserService userService = new UserService();
    private final CartService cartService = new CartService();
    private final PaymentService paymentService = new PaymentService();

    @Override
    public void insert(Order order) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("INSERT INTO [ORDER](USER_ID, REC_NAME, REC_ADDRESS, REC_PHONE, ORD_DATE, REC_DATE, ORD_STATUS, ORD_SUMPRO, ORD_SHIPPING, ORD_TAX, ORD_SUBTOTAL, ORD_DISCOUNT, ORD_TOTAL, CART_ID, PAY_ID) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, order.getUser().getUserId());
            ps.setString(2, order.getRecipientName());
            ps.setString(3, order.getRecipientAddress());
            ps.setString(4, order.getRecipientPhone());
            ps.setTimestamp(5, order.getOrderDate());
            ps.setDate(6, order.getRecipientDate());
            ps.setString(7, order.getOrderStatus());
            ps.setInt(8, order.getOrderSumProduct());
            ps.setBigDecimal(9, order.getOrderShipping());
            ps.setBigDecimal(10, order.getOrderTax());
            ps.setBigDecimal(11, order.getOrderSubTotal());
            ps.setBigDecimal(12, order.getOrderDiscount());
            ps.setBigDecimal(13, order.getOrderTotal());
            ps.setString(14, order.getCart().getCartId());
            ps.setString(15, order.getPayment().getPaymentId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public void edit(Order order) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("UPDATE [ORDER] SET USER_ID = ?, REC_NAME = ?, REC_ADDRESS = ?, REC_PHONE = ?, ORD_DATE = ?, REC_DATE = ?, ORD_STATUS = ?, ORD_SUMPRO = ?, ORD_SHIPPING = ?, ORD_TAX = ?, ORD_SUBTOTAL = ?, ORD_DISCOUNT = ?, ORD_TOTAL = ?, CART_ID = ?, PAY_ID = ? WHERE ORD_ID = ?");
            ps.setString(1, order.getUser().getUserId());
            ps.setString(2, order.getRecipientName());
            ps.setString(3, order.getRecipientAddress());
            ps.setString(4, order.getRecipientPhone());
            ps.setTimestamp(5, order.getOrderDate());
            ps.setDate(6, order.getRecipientDate());
            ps.setString(7, order.getOrderStatus());
            ps.setInt(8, order.getOrderSumProduct());
            ps.setBigDecimal(9, order.getOrderShipping());
            ps.setBigDecimal(10, order.getOrderTax());
            ps.setBigDecimal(11, order.getOrderSubTotal());
            ps.setBigDecimal(12, order.getOrderDiscount());
            ps.setBigDecimal(13, order.getOrderTotal());
            ps.setString(14, order.getCart().getCartId());
            ps.setString(15, order.getPayment().getPaymentId());
            ps.setInt(16, order.getOrderId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public void delete(int orderId) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("DELETE FROM [ORDER] WHERE ORD_ID = ?");
            ps.setInt(1, orderId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public Order getOrder(int orderId) {
        conn = DBConnect.getConnection();
        Order order = new Order();

        try {
            ps = conn.prepareStatement("SELECT * FROM [ORDER] WHERE ORD_ID = ?");
            ps.setInt(1, orderId);
            rs = ps.executeQuery();
            rs.next();
            order.setOrderId(orderId);
            order.setUser(userService.getUser(rs.getString("USER_ID").trim()));
            order.setRecipientName(rs.getString("REC_NAME"));
            order.setRecipientAddress(rs.getString("REC_ADDRESS"));
            order.setRecipientPhone(rs.getString("REC_PHONE"));
            order.setOrderDate(rs.getTimestamp("ORD_DATE"));
            order.setRecipientDate(rs.getDate("REC_DATE"));
            order.setOrderStatus(rs.getString("ORD_STATUS"));
            order.setOrderSumProduct(rs.getInt("ORD_SUMPRO"));
            order.setOrderShipping(rs.getBigDecimal("ORD_SHIPPING"));
            order.setOrderTax(rs.getBigDecimal("ORD_TAX"));
            order.setOrderSubTotal(rs.getBigDecimal("ORD_SUBTOTAL"));
            order.setOrderDiscount(rs.getBigDecimal("ORD_DISCOUNT"));
            order.setOrderTotal(rs.getBigDecimal("ORD_TOTAL"));
            order.setCart(cartService.getCart(rs.getString("CART_ID").trim()));
            order.setPayment(paymentService.getPayment(rs.getString("PAY_ID").trim()));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return order;
    }

    @Override
    public Order getNewestOrder() {
        conn = DBConnect.getConnection();
        Order order = new Order();

        try {
            ps = conn.prepareStatement("SELECT * FROM [ORDER] WHERE ORD_ID = (SELECT MAX(ORD_ID) FROM [ORDER])");
            rs = ps.executeQuery();
            rs.next();
            order.setOrderId(rs.getInt("ORD_ID"));
            order.setUser(userService.getUser(rs.getString("USER_ID").trim()));
            order.setRecipientName(rs.getString("REC_NAME"));
            order.setRecipientAddress(rs.getString("REC_ADDRESS"));
            order.setRecipientPhone(rs.getString("REC_PHONE"));
            order.setOrderDate(rs.getTimestamp("ORD_DATE"));
            order.setRecipientDate(rs.getDate("REC_DATE"));
            order.setOrderStatus(rs.getString("ORD_STATUS"));
            order.setOrderSumProduct(rs.getInt("ORD_SUMPRO"));
            order.setOrderShipping(rs.getBigDecimal("ORD_SHIPPING"));
            order.setOrderTax(rs.getBigDecimal("ORD_TAX"));
            order.setOrderSubTotal(rs.getBigDecimal("ORD_SUBTOTAL"));
            order.setOrderDiscount(rs.getBigDecimal("ORD_DISCOUNT"));
            order.setOrderTotal(rs.getBigDecimal("ORD_TOTAL"));
            order.setCart(cartService.getCart(rs.getString("CART_ID").trim()));
            order.setPayment(paymentService.getPayment(rs.getString("PAY_ID").trim()));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return order;
    }

    @Override
    public List<Order> getAll() {
        conn = DBConnect.getConnection();
        List<Order> orders = new ArrayList<>();

        try {
            ps = conn.prepareStatement("SELECT * FROM [ORDER]");
            rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("ORD_ID"));
                order.setUser(userService.getUser(rs.getString("USER_ID").trim()));
                order.setRecipientName(rs.getString("REC_NAME"));
                order.setRecipientAddress(rs.getString("REC_ADDRESS"));
                order.setRecipientPhone(rs.getString("REC_PHONE"));
                order.setOrderDate(rs.getTimestamp("ORD_DATE"));
                order.setRecipientDate(rs.getDate("REC_DATE"));
                order.setOrderStatus(rs.getString("ORD_STATUS"));
                order.setOrderSumProduct(rs.getInt("ORD_SUMPRO"));
                order.setOrderShipping(rs.getBigDecimal("ORD_SHIPPING"));
                order.setOrderTax(rs.getBigDecimal("ORD_TAX"));
                order.setOrderSubTotal(rs.getBigDecimal("ORD_SUBTOTAL"));
                order.setOrderDiscount(rs.getBigDecimal("ORD_DISCOUNT"));
                order.setOrderTotal(rs.getBigDecimal("ORD_TOTAL"));
                order.setCart(cartService.getCart(rs.getString("CART_ID").trim()));
                order.setPayment(paymentService.getPayment(rs.getString("PAY_ID").trim()));

                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return orders;
    }
}
