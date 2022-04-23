package Services;

import Entity.Report;

import java.sql.Timestamp;
import java.util.List;

public interface IReportService {
    List<Report> getReports(Timestamp from, Timestamp to, boolean status);
    List<Report> getReportsUndone(Timestamp from, Timestamp to);
    Report getSum(Timestamp from, Timestamp to, boolean status);
    Report getSumUndone(Timestamp from, Timestamp to);
}
