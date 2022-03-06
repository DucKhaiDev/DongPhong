package Dao.deploy;

import Connect.DBConnect;
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

public class OrderDao implements Dao.OrderDao {
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
            ps = conn.prepareStatement("INSERT INTO [ORDER](ORD_ID, USER_ID, REC_NAME, REC_ADDRESS, REC_PHONE, ORD_DATE, REC_DATE, ORD_STATUS, ORD_TOTALPRO, ORD_TOTALPAY, CART_ID, PAY_ID) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, order.getOrderId());
            ps.setString(2, order.getUser().getUserId());
            ps.setString(3, order.getRecipientName());
            ps.setString(4, order.getRecipientAddress());
            ps.setString(5, order.getRecipientPhone());
            ps.setTimestamp(6, order.getOrderDate());
            ps.setDate(7, order.getRecipientDate());
            ps.setString(8, order.getOrderStatus());
            ps.setInt(9, order.getOrderTotalProduct());
            ps.setString(10, order.getOrderTotalPayment());
            ps.setString(11, order.getCart().getCartId());
            ps.setString(12, order.getPayment().getPaymentId());

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
            ps = conn.prepareStatement("UPDATE [ORDER] SET USER_ID = ?, REC_NAME = ?, REC_ADDRESS = ?, REC_PHONE = ?, ORD_DATE = ?, REC_DATE = ?, ORD_STATUS = ?, ORD_TOTALPRO = ?, ORD_TOTALPAY = ?, CART_ID = ?, PAY_ID = ? WHERE ORD_ID = ?");
            ps.setString(1, order.getUser().getUserId());
            ps.setString(2, order.getRecipientName());
            ps.setString(3, order.getRecipientAddress());
            ps.setString(4, order.getRecipientPhone());
            ps.setTimestamp(5, order.getOrderDate());
            ps.setDate(6, order.getRecipientDate());
            ps.setString(7, order.getOrderStatus());
            ps.setInt(8, order.getOrderTotalProduct());
            ps.setString(9, order.getOrderTotalPayment());
            ps.setString(10, order.getCart().getCartId());
            ps.setString(11, order.getPayment().getPaymentId());
            ps.setString(12, order.getOrderId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public void delete(String orderId) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("DELETE FROM [ORDER] WHERE ORD_ID = ?");
            ps.setString(1, orderId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public Order getOrder(String orderId) {
        conn = DBConnect.getConnection();
        Order order = new Order();

        try {
            ps = conn.prepareStatement("SELECT * FROM [ORDER] WHERE ORD_ID = ?");
            ps.setString(1, orderId);
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
            order.setOrderTotalProduct(rs.getInt("ORD_TOTALPRO"));
            order.setOrderTotalPayment(rs.getString("ORD_TOTALPAY"));
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
                order.setOrderId(rs.getString("ORD_ID").trim());
                order.setUser(userService.getUser(rs.getString("USER_ID").trim()));
                order.setRecipientName(rs.getString("REC_NAME"));
                order.setRecipientAddress(rs.getString("REC_ADDRESS"));
                order.setRecipientPhone(rs.getString("REC_PHONE"));
                order.setOrderDate(rs.getTimestamp("ORD_DATE"));
                order.setRecipientDate(rs.getDate("REC_DATE"));
                order.setOrderStatus(rs.getString("ORD_STATUS"));
                order.setOrderTotalProduct(rs.getInt("ORD_TOTALPRO"));
                order.setOrderTotalPayment(rs.getString("ORD_TOTALPAY"));
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
