package Controller.Admin;

import Entity.Voucher;
import Services.deploy.VoucherService;
import Util.Constant;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(name = "EditVoucher", value = "/admin/voucher/edit")
public class EditVoucher extends HttpServlet {
    private final VoucherService voucherService = new VoucherService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String voucherId = request.getParameter("id");
        Voucher voucher = new VoucherService().getVoucher(voucherId);
        request.setAttribute("voucher", voucher);
        request.getRequestDispatcher(Constant.Path.ADMIN_EDIT_VOUCHER).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String voucherId = request.getParameter("voucherId");
        int minProduct = Integer.parseInt(request.getParameter("minProduct"));
        BigDecimal minValue = new BigDecimal(request.getParameter("minValue"));
        double discount = Double.parseDouble(request.getParameter("discount"))/100;
        Timestamp fromDate = new Timestamp(System.currentTimeMillis());
        Timestamp toDate = new Timestamp(System.currentTimeMillis());
        try {
            fromDate = new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("fromDate")).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            toDate = new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("toDate")).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Voucher voucher = new Voucher(voucherId, minProduct, minValue, discount, fromDate, toDate);
        voucherService.edit(voucher);
        response.sendRedirect(request.getContextPath() + "/admin/voucher/edit?id=" + voucherId);
    }
}
