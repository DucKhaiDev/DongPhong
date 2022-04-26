package Dao;

import Entity.Report;
import Tools.Pair;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public interface IReportDao {
    List<Report> getReports(Timestamp from, Timestamp to, boolean status);

    List<Report> getReportsUndone(Timestamp from, Timestamp to);

    Report getSum(Timestamp from, Timestamp to, boolean status);

    Report getSumUndone(Timestamp from, Timestamp to);

    List<Pair<Integer, BigDecimal>> getReportYears(int from, int to);
}