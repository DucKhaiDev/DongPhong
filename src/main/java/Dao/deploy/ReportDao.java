package Dao.deploy;

import Connect.DBConnect;
import Dao.IReportDao;
import Entity.Report;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportDao implements IReportDao {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    @Override
    public List<Report> getReports(Timestamp from, Timestamp to, boolean status) {
        conn = DBConnect.getConnection();
        List<Report> reports = new ArrayList<>();

        try {
            ps = conn.prepareStatement("SELECT MONTH(ORD_DATE), YEAR(ORD_DATE), COUNT(ORD_ID), SUM(ORD_SUBTOTAL), SUM(ORD_DISCOUNT), SUM(ORD_TAX), SUM(ORD_SHIPPING), SUM(ORD_TOTAL) " +
                    "FROM dbo.[ORDER] " +
                    "WHERE ? <= ORD_DATE AND ORD_DATE <= DATEADD(MONTH, 1, ?) AND ORD_STATUS = ? " +
                    "GROUP BY MONTH(ORD_DATE), YEAR(ORD_DATE)");
            ps.setTimestamp(1, from);
            ps.setTimestamp(2, to);
            ps.setBoolean(3, status);
            rs = ps.executeQuery();
            while (rs.next()) {
                Report report = new Report();
                report.setMonthDate(rs.getInt(1));
                report.setYearDate(rs.getInt(2));
                report.setCountId(rs.getInt(3));
                report.setSumSubTotal(rs.getBigDecimal(4));
                report.setSumDiscount(rs.getBigDecimal(5));
                report.setSumTax(rs.getBigDecimal(6));
                report.setSumShipping(rs.getBigDecimal(7));
                report.setSumTotal(rs.getBigDecimal(8));

                reports.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return reports;
    }

    @Override
    public List<Report> getReportsUndone(Timestamp from, Timestamp to) {
        conn = DBConnect.getConnection();
        List<Report> reports = new ArrayList<>();

        try {
            ps = conn.prepareStatement("SELECT MONTH(ORD_DATE), YEAR(ORD_DATE), COUNT(ORD_ID), SUM(ORD_SUBTOTAL), SUM(ORD_DISCOUNT), SUM(ORD_TAX), SUM(ORD_SHIPPING), SUM(ORD_TOTAL) " +
                    "FROM dbo.[ORDER] " +
                    "WHERE ? <= ORD_DATE AND ORD_DATE <= DATEADD(MONTH, 1, ?) AND ORD_STATUS IS NULL " +
                    "GROUP BY MONTH(ORD_DATE), YEAR(ORD_DATE)");
            ps.setTimestamp(1, from);
            ps.setTimestamp(2, to);
            rs = ps.executeQuery();
            while (rs.next()) {
                Report report = new Report();
                report.setMonthDate(rs.getInt(1));
                report.setYearDate(rs.getInt(2));
                report.setCountId(rs.getInt(3));
                report.setSumSubTotal(rs.getBigDecimal(4));
                report.setSumDiscount(rs.getBigDecimal(5));
                report.setSumTax(rs.getBigDecimal(6));
                report.setSumShipping(rs.getBigDecimal(7));
                report.setSumTotal(rs.getBigDecimal(8));

                reports.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return reports;
    }

    @Override
    public Report getSum(Timestamp from, Timestamp to, boolean status) {
        conn = DBConnect.getConnection();
        Report report = new Report();

        try {
            ps = conn.prepareStatement("SELECT COUNT(ORD_ID), SUM(ORD_SUBTOTAL), SUM(ORD_DISCOUNT), SUM(ORD_TAX), SUM(ORD_SHIPPING), SUM(ORD_TOTAL) " +
                    "FROM dbo.[ORDER] " +
                    "WHERE ? <= ORD_DATE AND ORD_DATE <= DATEADD(MONTH, 1, ?) AND ORD_STATUS = ?");
            ps.setTimestamp(1, from);
            ps.setTimestamp(2, to);
            ps.setBoolean(3, status);
            rs = ps.executeQuery();
            while (rs.next()) {
                report.setCountId(rs.getInt(1));
                report.setSumSubTotal(rs.getBigDecimal(2));
                report.setSumDiscount(rs.getBigDecimal(3));
                report.setSumTax(rs.getBigDecimal(4));
                report.setSumShipping(rs.getBigDecimal(5));
                report.setSumTotal(rs.getBigDecimal(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return report;
    }

    @Override
    public Report getSumUndone(Timestamp from, Timestamp to) {
        conn = DBConnect.getConnection();
        Report report = new Report();

        try {
            ps = conn.prepareStatement("SELECT COUNT(ORD_ID), SUM(ORD_SUBTOTAL), SUM(ORD_DISCOUNT), SUM(ORD_TAX), SUM(ORD_SHIPPING), SUM(ORD_TOTAL) " +
                    "FROM dbo.[ORDER] " +
                    "WHERE ? <= ORD_DATE AND ORD_DATE <= DATEADD(MONTH, 1, ?) AND ORD_STATUS IS NULL");
            ps.setTimestamp(1, from);
            ps.setTimestamp(2, to);
            rs = ps.executeQuery();
            while (rs.next()) {
                report.setCountId(rs.getInt(1));
                report.setSumSubTotal(rs.getBigDecimal(2));
                report.setSumDiscount(rs.getBigDecimal(3));
                report.setSumTax(rs.getBigDecimal(4));
                report.setSumShipping(rs.getBigDecimal(5));
                report.setSumTotal(rs.getBigDecimal(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return report;
    }
}
