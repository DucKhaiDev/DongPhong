package Dao;

import Entity.Report;
import Tools.Pair;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public interface IReportDao {
    List<Report> getReports(Timestamp from, Timestamp to, byte status);

    Report getSum(Timestamp from, Timestamp to, byte status);

    List<Pair<Integer, BigDecimal>> getReportYears(int from, int to);
}