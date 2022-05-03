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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@WebServlet(name = "StatisticController", value = "/admin/statistic")
public class StatisticController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!LoginController.checkLoginAdmin(request, response)) {
            return;
        }

        Timestamp fromDate = new Timestamp(Date.from(LocalDate.now().minusDays(30).atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime());
        String fromDateParam = request.getParameter("fromDate");
        if (fromDateParam != null && !fromDateParam.trim().isEmpty()) {
            try {
                fromDate = new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(fromDateParam).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        request.setAttribute("fromDate", fromDate);

        Timestamp toDate = new Timestamp(new Date().getTime());
        String toDateParam = request.getParameter("toDate");
        if (toDateParam != null && !toDateParam.trim().isEmpty()) {
            try {
                toDate = new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(toDateParam).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        request.setAttribute("toDate", toDate);

        request.setAttribute("bestseller", Constant.Service.PRODUCT_SERVICE.bestseller(fromDate, toDate));
        request.setAttribute("favourite", Constant.Service.PRODUCT_SERVICE.favourite());
        request.setAttribute("highestRated", Constant.Service.PRODUCT_SERVICE.highestRated());

        request.getRequestDispatcher(Constant.Path.ADMIN_STATISTIC).forward(request, response);
    }
}