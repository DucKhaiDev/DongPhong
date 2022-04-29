package Services.deploy;

import Dao.deploy.ReportDao;
import Entity.Report;
import Services.IReportService;
import Tools.Pair;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class ReportService implements IReportService {
    private final ReportDao reportDao = new ReportDao();

    @Override
    public List<Report> getReports(Timestamp from, Timestamp to, byte status) {
        return reportDao.getReports(from, to, status);
    }

    @Override
    public Report getSum(Timestamp from, Timestamp to, byte status) {
        return reportDao.getSum(from, to, status);
    }

    @Override
    public List<Pair<Integer, BigDecimal>> getReportYears(int from, int to) {
        return reportDao.getReportYears(from, to);
    }
}