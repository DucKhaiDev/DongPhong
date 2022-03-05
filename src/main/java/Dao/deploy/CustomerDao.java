package Dao.deploy;

import Connect.DBConnect;
import Entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao implements Dao.CustomerDao {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    @Override
    public void insert(Customer customer) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("INSERT INTO [CUSTOMER](CUS_ID, CUS_FNAME, CUS_LNAME, CUS_EMAIL, CUS_ADDRESS, CUS_PHONE) VALUES(?, ?, ?, ?, ?, ?)");
            ps.setString(1, customer.getCUS_ID());
            ps.setString(2, customer.getCUS_FNAME());
            ps.setString(3, customer.getCUS_LNAME());
            ps.setString(4, customer.getCUS_EMAIL());
            ps.setString(5, customer.getCUS_ADDRESS());
            ps.setString(6, customer.getCUS_PHONE());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public void edit(Customer customer) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("UPDATE [CUSTOMER] SET CUS_FNAME = ?, CUS_LNAME = ?, CUS_EMAIL = ?, CUS_ADDRESS = ?, CUS_PHONE = ? WHERE CUS_ID = ?");
            ps.setString(1, customer.getCUS_FNAME());
            ps.setString(2, customer.getCUS_LNAME());
            ps.setString(3, customer.getCUS_EMAIL());
            ps.setString(4, customer.getCUS_ADDRESS());
            ps.setString(5, customer.getCUS_PHONE());
            ps.setString(6, customer.getCUS_ID());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public void delete(String CUS_ID) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("DELETE FROM [CUSTOMER] WHERE CUS_ID = ?");
            ps.setString(1, CUS_ID);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
    }

    @Override
    public Customer getCustomer(String CUS_ID) {
        conn = DBConnect.getConnection();
        Customer customer = new Customer();

        try {
            ps = conn.prepareStatement("SELECT * FROM [CUSTOMER] WHERE CUS_ID = ?");
            ps.setString(1, CUS_ID);

            rs = ps.executeQuery();
            rs.next();
            customer.setCUS_ID(CUS_ID);
            customer.setCUS_FNAME(rs.getString("CUS_FNAME"));
            customer.setCUS_LNAME(rs.getString("CUS_LNAME"));
            customer.setCUS_EMAIL(rs.getString("CUS_EMAIL"));
            customer.setCUS_ADDRESS(rs.getString("CUS_ADDRESS"));
            customer.setCUS_PHONE(rs.getString("CUS_PHONE"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return customer;
    }

    @Override
    public List<Customer> getAll() {
        conn = DBConnect.getConnection();
        List<Customer> customers = new ArrayList<>();

        try {
            ps = conn.prepareStatement("SELECT * FROM [CUSTOMER]");
            rs = ps.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCUS_ID(rs.getString("CUS_ID").trim());
                customer.setCUS_FNAME(rs.getString("CUS_FNAME"));
                customer.setCUS_LNAME(rs.getString("CUS_LNAME"));
                customer.setCUS_EMAIL(rs.getString("CUS_EMAIL"));
                customer.setCUS_ADDRESS(rs.getString("CUS_ADDRESS"));
                customer.setCUS_PHONE(rs.getString("CUS_PHONE"));

                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return customers;
    }

    @Override
    public boolean checkExistID(String ID) {
        conn = DBConnect.getConnection();

        try {
            ps = conn.prepareStatement("SELECT * FROM [CUSTOMER] WHERE CUS_ID = ?");
            ps.setString(1, ID);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return false;
    }
}
