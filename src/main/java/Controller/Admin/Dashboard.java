package Controller.Admin;

import Controller.Client.LoginController;
import Util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@WebServlet(name = "Dashboard", value = "/admin")
public class Dashboard extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (LoginController.checkLogin(request, response)) {
            return;
        }

        //Get report from year to year
        int fromYear = Integer.parseInt(new SimpleDateFormat("yyyy").format(new Timestamp(Date.from(LocalDate.now().minusYears(10).atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime())));
        int toYear = Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()));
        Timestamp from = new Timestamp(Date.from(LocalDate.now().minusWeeks(1).atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime());
        request.setAttribute("fromYear", fromYear);
        request.setAttribute("toYear", toYear);
        request.setAttribute("reports", Constant.Service.REPORT_SERVICE.getReportYears(fromYear, toYear));
        request.setAttribute("countNewMessage", Constant.Service.MESSAGE_SERVICE.countNewMessage(from));
        request.setAttribute("countNewComment", Constant.Service.REVIEW_SERVICE.countNewComment(from));
        request.setAttribute("topMembers", Constant.Service.USER_SERVICE.getTopMember());
        request.getRequestDispatcher(Constant.Path.DASHBOARD).forward(request, response);
    }
}