package Controller.Admin;

import Controller.Client.LoginController;
import Entity.Report;
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
import java.util.List;

@WebServlet(name = "ReportController", value = "/admin/report")
public class ReportController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (LoginController.checkLoginAdmin(request, response)) {
            return;
        }

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
        request.setAttribute("reportDone", Constant.Service.REPORT_SERVICE.getReports(from, to, (byte) 3));
        request.setAttribute("done", Constant.Service.REPORT_SERVICE.getSum(from, to, (byte) 3));

        List<Report> reportUndone = Constant.Service.REPORT_SERVICE.getReports(from, to, (byte) 1);
        reportUndone.addAll(Constant.Service.REPORT_SERVICE.getReports(from, to, (byte) 2));

        Report undone_1 = Constant.Service.REPORT_SERVICE.getSum(from, to, (byte) 1);
        Report undone_2 = Constant.Service.REPORT_SERVICE.getSum(from, to, (byte) 2);

        Report undone = new Report();
        undone.setCountId(undone_1.getCountId() + undone_2.getCountId());
        undone.setSumSubTotal(undone_1.getSumSubTotal().add(undone_2.getSumSubTotal()));
        undone.setSumDiscount(undone_1.getSumDiscount().add(undone_2.getSumDiscount()));
        undone.setSumTax(undone_1.getSumTax().add(undone_2.getSumTax()));
        undone.setSumShipping(undone_1.getSumShipping().add(undone_2.getSumShipping()));
        undone.setSumTotal(undone_1.getSumTotal().add(undone_2.getSumTotal()));

        request.setAttribute("reportUndone", reportUndone);
        request.setAttribute("undone", undone);
        request.setAttribute("reportCancel", Constant.Service.REPORT_SERVICE.getReports(from, to, (byte) 4));
        request.setAttribute("cancel", Constant.Service.REPORT_SERVICE.getSum(from, to, (byte) 4));

        request.getRequestDispatcher(Constant.Path.ADMIN_REPORT).forward(request, response);
    }
}