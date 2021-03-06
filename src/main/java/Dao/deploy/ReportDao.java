package Dao.deploy;

import Connect.DBConnect;
import Dao.IReportDao;
import Entity.Report;
import Tools.Pair;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportDao implements IReportDao {
    @Override
    public List<Report> getReports(Timestamp from, Timestamp to, byte status) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Report> reports = new ArrayList<>();

        try {
            ps = conn.prepareStatement("SELECT MONTH(ORD_DATE), YEAR(ORD_DATE), COUNT(ORD_ID), SUM(ORD_SUBTOTAL), SUM(ORD_DISCOUNT), SUM(ORD_TAX), SUM(ORD_SHIPPING), SUM(ORD_TOTAL) " +
                    "FROM dbo.[ORDER] " +
                    "WHERE ? <= ORD_DATE AND ORD_DATE <= DATEADD(MONTH, 1, ?) AND ORD_STATUS = ? " +
                    "GROUP BY MONTH(ORD_DATE), YEAR(ORD_DATE)");
            ps.setTimestamp(1, from);
            ps.setTimestamp(2, to);
            ps.setByte(3, status);
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
    public Report getSum(Timestamp from, Timestamp to, byte status) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Report report = new Report();

        try {
            ps = conn.prepareStatement("SELECT COUNT(ORD_ID), SUM(ORD_SUBTOTAL), SUM(ORD_DISCOUNT), SUM(ORD_TAX), SUM(ORD_SHIPPING), SUM(ORD_TOTAL) " +
                    "FROM dbo.[ORDER] " +
                    "WHERE ? <= ORD_DATE AND ORD_DATE <= DATEADD(MONTH, 1, ?) AND ORD_STATUS = ?");
            ps.setTimestamp(1, from);
            ps.setTimestamp(2, to);
            ps.setByte(3, status);
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
    public List<Pair<Integer, BigDecimal>> getReportYears(int from, int to) {
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Pair<Integer, BigDecimal>> reports = new ArrayList<>();

        try {
            ps = conn.prepareStatement("SELECT YEAR(ORD_DATE), SUM(ORD_TOTAL) " +
                    "FROM dbo.[ORDER] " +
                    "WHERE ? <= YEAR(ORD_DATE) AND YEAR(ORD_DATE) <= ? AND  ORD_STATUS = 3 " +
                    "GROUP BY YEAR(ORD_DATE) " +
                    "ORDER BY YEAR(ORD_DATE) ASC");
            ps.setInt(1, from);
            ps.setInt(2, to);
            rs = ps.executeQuery();
            while (rs.next()) {
                Pair<Integer, BigDecimal> pair = new Pair<>();
                pair.setKey(rs.getInt(1));
                pair.setValue(rs.getBigDecimal(2));

                reports.add(pair);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }

        return reports;
    }
}