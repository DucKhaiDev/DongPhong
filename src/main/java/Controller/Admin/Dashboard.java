package Controller.Admin;

import Util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "Dashboard", value = "/admin")
public class Dashboard extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Get report from year to year
        int fromYear = 2018;
        int toYear = Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()));
        request.setAttribute("fromYear", fromYear);
        request.setAttribute("toYear", toYear);
        request.setAttribute("reports", Constant.Service.REPORT_SERVICE.getReportYears(fromYear, toYear));
        request.getRequestDispatcher(Constant.Path.DASHBOARD).forward(request, response);
    }
}