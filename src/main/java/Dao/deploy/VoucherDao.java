package Dao.deploy;

import Connect.DBConnect;
import Dao.IVoucherDao;
import Entity.Voucher;
import Util.Constant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VoucherDao implements IVoucherDao {
    @Override
    public void insert(Voucher voucher) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("INSERT INTO [VOUCHER](" +
                    "VCR_ID, MIN_PRO, MIN_VAL, DISCOUNT, DISC_MAX, QUANTITY, FROM_DATE, TO_DATE" +
                    ") " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, voucher.getVoucherId());
            ps.setInt(2, voucher.getMinProduct());
            ps.setBigDecimal(3, voucher.getMinValue());
            ps.setDouble(4, voucher.getDiscount());
            ps.setBigDecimal(5, voucher.getDiscountMax());
            ps.setInt(6, voucher.getQuantity());
            ps.setTimestamp(7, voucher.getFromDate());
            ps.setTimestamp(8, voucher.getToDate());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(null, ps, conn);
        }
    }

    @Override
    public void edit(Voucher voucher) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("UPDATE [VOUCHER] " +
                    "SET MIN_PRO = ?, MIN_VAL = ?, DISCOUNT = ?, DISC_MAX = ?, QUANTITY = ?, FROM_DATE = ?, TO_DATE = ? " +
                    "WHERE VCR_ID = ?");
            ps.setInt(1, voucher.getMinProduct());
            ps.setBigDecimal(2, voucher.getMinValue());
            ps.setDouble(3, voucher.getDiscount());
            ps.setBigDecimal(4, voucher.getDiscountMax());
            ps.setInt(5, voucher.getQuantity());
            ps.setTimestamp(6, voucher.getFromDate());
            ps.setTimestamp(7, voucher.getToDate());
            ps.setString(8, voucher.getVoucherId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(null, ps, conn);
        }
    }

    @Override
    public void delete(String voucherId) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("DELETE FROM [VOUCHER] WHERE VCR_ID = ?");
            ps.setString(1, voucherId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(null, ps, conn);
        }
    }

    @Override
    public Voucher getVoucher(String voucherId) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Voucher voucher = new Voucher();

        try {
            ps = conn.prepareStatement("SELECT * FROM [VOUCHER] WHERE VCR_ID = ?");
            ps.setString(1, voucherId);
            rs = ps.executeQuery();
            if (rs.next()) {
                voucher.setVoucherId(rs.getString("VCR_ID"));
                voucher.setMinProduct(rs.getInt("MIN_PRO"));
                voucher.setMinValue(rs.getBigDecimal("MIN_VAL"));
                voucher.setDiscount(rs.getDouble("DISCOUNT"));
                voucher.setDiscountMax(rs.getBigDecimal("DISC_MAX"));
                voucher.setQuantity(rs.getInt("QUANTITY"));
                voucher.setFromDate(rs.getTimestamp("FROM_DATE"));
                voucher.setToDate(rs.getTimestamp("TO_DATE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return voucher;
    }

    @Override
    public List<Voucher> getAll() {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Voucher> vouchers = new ArrayList<>();

        try {
            ps = conn.prepareStatement("SELECT * FROM [VOUCHER] ORDER BY VCR_ID ASC");
            rs = ps.executeQuery();
            while (rs.next()) {
                Voucher voucher = new Voucher();
                voucher.setVoucherId(rs.getString("VCR_ID"));
                voucher.setMinProduct(rs.getInt("MIN_PRO"));
                voucher.setMinValue(rs.getBigDecimal("MIN_VAL"));
                voucher.setDiscount(rs.getDouble("DISCOUNT"));
                voucher.setDiscountMax(rs.getBigDecimal("DISC_MAX"));
                voucher.setQuantity(rs.getInt("QUANTITY"));
                voucher.setFromDate(rs.getTimestamp("FROM_DATE"));
                voucher.setToDate(rs.getTimestamp("TO_DATE"));

                vouchers.add(voucher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return vouchers;
    }

    @Override
    public List<String> getAllVoucher() {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> vouchers = new ArrayList<>();

        try {
            ps = conn.prepareStatement("SELECT VCR_ID FROM [VOUCHER]");
            rs = ps.executeQuery();
            while (rs.next()) {
                vouchers.add(rs.getString("VCR_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return vouchers;
    }

    @Override
    public boolean checkExistId(String voucherId) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM [VOUCHER] WHERE VCR_ID = ?");
            ps.setString(1, voucherId);
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

    @Override
    public List<Voucher> getAvailableVoucher(String cartId) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Voucher> vouchers = new ArrayList<>();

        try {
            ps = conn.prepareStatement("SELECT * " +
                    "FROM dbo.VOUCHER " +
                    "WHERE ? >= MIN_PRO AND ? >= MIN_VAL AND QUANTITY > 0 AND FROM_DATE <= GETDATE() AND GETDATE() <= TO_DATE");
            ps.setInt(1, Constant.Service.CART_ITEM_SERVICE.countSumQuantity(cartId));
            ps.setBigDecimal(2, Constant.Service.CART_ITEM_SERVICE.getSubTotal(cartId));
            rs = ps.executeQuery();
            while (rs.next()) {
                Voucher voucher = new Voucher();
                voucher.setVoucherId(rs.getString("VCR_ID"));
                voucher.setMinProduct(rs.getInt("MIN_PRO"));
                voucher.setMinValue(rs.getBigDecimal("MIN_VAL"));
                voucher.setDiscount(rs.getDouble("DISCOUNT"));
                voucher.setDiscountMax(rs.getBigDecimal("DISC_MAX"));
                voucher.setQuantity(rs.getInt("QUANTITY"));
                voucher.setFromDate(rs.getTimestamp("FROM_DATE"));
                voucher.setToDate(rs.getTimestamp("TO_DATE"));

                vouchers.add(voucher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return vouchers;
    }
}