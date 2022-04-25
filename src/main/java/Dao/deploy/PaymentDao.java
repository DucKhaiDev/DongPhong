package Dao.deploy;

import Connect.DBConnect;
import Dao.IPaymentDao;
import Entity.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDao implements IPaymentDao {
    @Override
    public void insert(Payment payment) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("INSERT INTO [PAYMENT](PAY_ID, PAY_METHOD, PAY_STATUS) VALUES(?, ?, ?)");
            ps.setString(1, payment.getPaymentId());
            ps.setString(2, payment.getPaymentMethod());
            ps.setBoolean(3, payment.getPaymentStatus());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(null, ps, conn);
        }
    }

    @Override
    public void edit(Payment payment) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("UPDATE [PAYMENT] SET PAY_METHOD = ?, PAY_STATUS = ? WHERE PAY_ID = ?");
            ps.setString(1, payment.getPaymentMethod());
            ps.setBoolean(2, payment.getPaymentStatus());
            ps.setString(3, payment.getPaymentId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(null, ps, conn);
        }
    }

    @Override
    public void delete(String PAY_ID) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("DELETE FROM [PAYMENT] WHERE PAY_ID = ?");
            ps.setString(1, PAY_ID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(null, ps, conn);
        }
    }

    @Override
    public Payment getPayment(String PAY_ID) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Payment payment = new Payment();

        try {
            ps = conn.prepareStatement("SELECT * FROM [PAYMENT] WHERE PAY_ID = ?");
            ps.setString(1, PAY_ID);
            rs = ps.executeQuery();
            if (rs.next()) {
                payment.setPaymentId(PAY_ID);
                payment.setPaymentMethod(rs.getString("PAY_METHOD"));
                payment.setPaymentStatus(rs.getBoolean("PAY_STATUS"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return payment;
    }

    @Override
    public List<Payment> getAll() {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Payment> payments = new ArrayList<>();

        try {
            ps = conn.prepareStatement("SELECT * FROM [PAYMENT] ORDER BY PAY_ID ASC");
            rs = ps.executeQuery();
            while (rs.next()) {
                Payment payment = new Payment();
                payment.setPaymentId(rs.getString("PAY_ID").trim());
                payment.setPaymentMethod(rs.getString("PAY_METHOD"));
                payment.setPaymentStatus(rs.getBoolean("PAY_STATUS"));

                payments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return payments;
    }
}