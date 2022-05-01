package Controller.Admin;

import Entity.Voucher;
import Util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(name = "AddVoucher", value = "/admin/voucher/add")
public class AddVoucher extends HttpServlet {
    public static Timestamp setFromDate(HttpServletRequest request) throws ServletException, IOException {
        Timestamp fromDate = new Timestamp(System.currentTimeMillis());

        try {
            fromDate = new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("fromDate")).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return fromDate;
    }

    public static Timestamp setToDate(HttpServletRequest request) throws ServletException, IOException {
        Timestamp toDate = new Timestamp(System.currentTimeMillis());

        try {
            toDate = new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("toDate")).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return toDate;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Constant.Path.ADMIN_ADD_VOUCHER).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String voucherId = request.getParameter("voucherId");
        if (Constant.Service.VOUCHER_SERVICE.checkExistId(voucherId)) {
            request.setAttribute("existId", "Mã giảm giá đã tồn tại!");
            request.getRequestDispatcher(Constant.Path.ADMIN_ADD_VOUCHER).forward(request, response);
            return;
        }

        int minProduct = Integer.parseInt(request.getParameter("minProduct"));
        BigDecimal minValue = new BigDecimal(request.getParameter("minValue"));
        double discount = Double.parseDouble(request.getParameter("discount")) / 100;
        BigDecimal discountMax = new BigDecimal(request.getParameter("discountMax"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Timestamp fromDate = AddVoucher.setFromDate(request);
        Timestamp toDate = AddVoucher.setToDate(request);

        Constant.Service.VOUCHER_SERVICE.insert(new Voucher(voucherId, minProduct, minValue, discount, discountMax, quantity, fromDate, toDate));

        response.sendRedirect(request.getContextPath() + "/admin/voucher");
    }
}