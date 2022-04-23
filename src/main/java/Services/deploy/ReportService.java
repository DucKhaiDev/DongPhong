package Services.deploy;

import Dao.deploy.ReportDao;
import Entity.Report;
import Services.IReportService;

import java.sql.Timestamp;
import java.util.List;

public class ReportService implements IReportService {
    private final ReportDao reportDao = new ReportDao();

    @Override
    public List<Report> getReports(Timestamp from, Timestamp to, boolean status) {
        return reportDao.getReports(from, to, status);
    }

    @Override
    public List<Report> getReportsUndone(Timestamp from, Timestamp to) {
        return reportDao.getReportsUndone(from, to);
    }

    @Override
    public Report getSum(Timestamp from, Timestamp to, boolean status) {
        return reportDao.getSum(from, to, status);
    }

    @Override
    public Report getSumUndone(Timestamp from, Timestamp to) {
        return reportDao.getSumUndone(from, to);
    }
}
