package Dao.deploy;

import Connect.DBConnect;
import Entity.Payment;
import com.oracle.wls.shaded.org.apache.regexp.RE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDao implements Dao.PaymentDao {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    @Override
    public void insert(Payment payment) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("INSERT INTO [PAYMENT](PAY_ID, PAY_METHOD) VALUES(?, ?)");
            ps.setString(1, payment.getPAY_ID());
            ps.setString(2, payment.getPAY_METHOD());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public void edit(Payment payment) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("UPDATE [PAYMENT] SET PAY_METHOD = ? WHERE PAY_ID = ?");
            ps.setString(1, payment.getPAY_METHOD());
            ps.setString(2, payment.getPAY_ID());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public void delete(String PAY_ID) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("DELETE FROM [PAYMENT] WHERE PAY_ID = ?");
            ps.setString(1, PAY_ID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public Payment getPayment(String PAY_ID) {
        conn = DBConnect.getConnection();
        Payment payment = new Payment();

        try {
            ps = conn.prepareStatement("SELECT * FROM [PAYMENT] WHERE PAY_ID = ?");
            ps.setString(1, PAY_ID);
            rs = ps.executeQuery();
            rs.next();
            payment.setPAY_ID(PAY_ID);
            payment.setPAY_METHOD(rs.getString("PAY_METHOD"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return payment;
    }

    @Override
    public List<Payment> getAll() {
        conn = DBConnect.getConnection();
        List<Payment> payments = new ArrayList<>();

        try {
            ps = conn.prepareStatement("SELECT * FROM [PAYMENT]");
            rs = ps.executeQuery();
            while (rs.next()) {
                Payment payment = new Payment();
                payment.setPAY_ID(rs.getString("PAY_ID"));
                payment.setPAY_METHOD(rs.getString("PAY_METHOD"));

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
