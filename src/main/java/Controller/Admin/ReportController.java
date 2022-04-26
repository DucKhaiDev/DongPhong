package Controller.Admin;

import Util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@WebServlet(name = "ReportController", value = "/admin/report")
public class ReportController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Timestamp from = new Timestamp(Date.from(LocalDate.now().minusMonths(12).atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime());
        String fromParam = request.getParameter("from");
        if (fromParam != null && !fromParam.trim().isEmpty()) {
            try {
                from = new Timestamp(new SimpleDateFormat("yyyy-MM").parse(fromParam).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        Timestamp to = new Timestamp(new Date().getTime());
        String toParam = request.getParameter("to");
        if (toParam != null && !toParam.trim().isEmpty()) {
            try {
                to = new Timestamp(new SimpleDateFormat("yyyy-MM").parse(toParam).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        request.setAttribute("from", from);
        request.setAttribute("to", to);
        request.setAttribute("reportDone", Constant.Service.REPORT_SERVICE.getReports(from, to, true));
        request.setAttribute("done", Constant.Service.REPORT_SERVICE.getSum(from, to, true));
        request.setAttribute("reportUndone", Constant.Service.REPORT_SERVICE.getReportsUndone(from, to));
        request.setAttribute("undone", Constant.Service.REPORT_SERVICE.getSumUndone(from, to));
        request.setAttribute("reportCancel", Constant.Service.REPORT_SERVICE.getReports(from, to, false));
        request.setAttribute("cancel", Constant.Service.REPORT_SERVICE.getSum(from, to, false));

        request.getRequestDispatcher(Constant.Path.ADMIN_REPORT).forward(request, response);
    }
}