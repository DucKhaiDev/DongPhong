package Dao.deploy;

import Connect.DBConnect;
import Entity.Order;
import Services.deploy.CartService;
import Services.deploy.CustomerService;
import Services.deploy.PaymentService;
import com.oracle.wls.shaded.org.apache.xpath.operations.Or;

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

    private final CustomerService customerService = new CustomerService();
    private final CartService cartService = new CartService();
    private final PaymentService paymentService = new PaymentService();

    @Override
    public void insert(Order order) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("INSERT INTO [ORDER](ORD_ID, CUS_ID, REC_NAME, REC_ADDRESS, REC_PHONE, ORD_DATE, REC_DATE, ORD_STATUS, ORD_TOTALPRO, ORD_TOTALPAY, CART_ID, PAY_ID) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, order.getORD_ID());
            ps.setString(2, order.getCUS().getCUS_ID());
            ps.setString(3, order.getREC_NAME());
            ps.setString(4, order.getREC_ADDRESS());
            ps.setString(5, order.getREC_PHONE());
            ps.setTimestamp(6, order.getORD_DATE());
            ps.setDate(7, order.getREC_DATE());
            ps.setString(8, order.getORD_STATUS());
            ps.setInt(9, order.getORD_TOTALPRO());
            ps.setString(10, order.getORD_TOTALPAY());
            ps.setString(11, order.getCART().getCART_ID());
            ps.setString(12, order.getPAY().getPAY_ID());

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
            ps = conn.prepareStatement("UPDATE [ORDER] SET CUS_ID = ?, REC_NAME = ?, REC_ADDRESS = ?, REC_PHONE = ?, ORD_DATE = ?, REC_DATE = ?, ORD_STATUS = ?, ORD_TOTALPRO = ?, ORD_TOTALPAY = ?, CART_ID = ?, PAY_ID = ? WHERE ORD_ID = ?");
            ps.setString(1, order.getCUS().getCUS_ID());
            ps.setString(2, order.getREC_NAME());
            ps.setString(3, order.getREC_ADDRESS());
            ps.setString(4, order.getREC_PHONE());
            ps.setTimestamp(5, order.getORD_DATE());
            ps.setDate(6, order.getREC_DATE());
            ps.setString(7, order.getORD_STATUS());
            ps.setInt(8, order.getORD_TOTALPRO());
            ps.setString(9, order.getORD_TOTALPAY());
            ps.setString(10, order.getCART().getCART_ID());
            ps.setString(11, order.getPAY().getPAY_ID());
            ps.setString(12, order.getORD_ID());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public void delete(String ORD_ID) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("DELETE FROM [ORDER] WHERE ORD_ID = ?");
            ps.setString(1, ORD_ID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public Order getOrder(String ORD_ID) {
        conn = DBConnect.getConnection();
        Order order = new Order();

        try {
            ps = conn.prepareStatement("SELECT * FROM [ORDER] WHERE ORD_ID = ?");
            ps.setString(1, ORD_ID);
            rs = ps.executeQuery();
            rs.next();
            order.setORD_ID(ORD_ID);
            order.setCUS(customerService.getCustomer(rs.getString("CUS_ID").trim()));
            order.setREC_NAME(rs.getString("REC_NAME"));
            order.setREC_ADDRESS(rs.getString("REC_ADDRESS"));
            order.setREC_PHONE(rs.getString("REC_PHONE"));
            order.setORD_DATE(rs.getTimestamp("ORD_DATE"));
            order.setREC_DATE(rs.getDate("REC_DATE"));
            order.setORD_STATUS(rs.getString("ORD_STATUS"));
            order.setORD_TOTALPRO(rs.getInt("ORD_TOTALPRO"));
            order.setORD_TOTALPAY(rs.getString("ORD_TOTALPAY"));
            order.setCART(cartService.getCart(rs.getString("CART_ID").trim()));
            order.setPAY(paymentService.getPayment(rs.getString("PAY_ID").trim()));
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
                order.setORD_ID(rs.getString("ORD_ID").trim());
                order.setCUS(customerService.getCustomer(rs.getString("CUS_ID").trim()));
                order.setREC_NAME(rs.getString("REC_NAME"));
                order.setREC_ADDRESS(rs.getString("REC_ADDRESS"));
                order.setREC_PHONE(rs.getString("REC_PHONE"));
                order.setORD_DATE(rs.getTimestamp("ORD_DATE"));
                order.setREC_DATE(rs.getDate("REC_DATE"));
                order.setORD_STATUS(rs.getString("ORD_STATUS"));
                order.setORD_TOTALPRO(rs.getInt("ORD_TOTALPRO"));
                order.setORD_TOTALPAY(rs.getString("ORD_TOTALPAY"));
                order.setCART(cartService.getCart(rs.getString("CART_ID").trim()));
                order.setPAY(paymentService.getPayment(rs.getString("PAY_ID").trim()));

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
