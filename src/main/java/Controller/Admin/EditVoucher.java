package Controller.Admin;

import Controller.Client.LoginController;
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

@WebServlet(name = "EditVoucher", value = "/admin/voucher/edit")
public class EditVoucher extends HttpServlet {
    private Voucher voucher;
    private String voucherId;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (LoginController.checkLogin(request, response)) {
            return;
        }

        voucherId = request.getParameter("id");

        voucher = Constant.Service.VOUCHER_SERVICE.getVoucher(voucherId);
        request.setAttribute("voucher", voucher);

        request.getRequestDispatcher(Constant.Path.ADMIN_EDIT_VOUCHER).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int minProduct = Integer.parseInt(request.getParameter("minProduct"));
        BigDecimal minValue = new BigDecimal(request.getParameter("minValue"));
        double discount = Double.parseDouble(request.getParameter("discount")) / 100;
        Timestamp fromDate = AddVoucher.setFromDate(request);
        Timestamp toDate = AddVoucher.setToDate(request);

        voucher.setMinProduct(minProduct);
        voucher.setMinValue(minValue);
        voucher.setDiscount(discount);
        voucher.setFromDate(fromDate);
        voucher.setToDate(toDate);

        Constant.Service.VOUCHER_SERVICE.edit(voucher);

        response.sendRedirect(request.getContextPath() + "/admin/voucher/edit?id=" + voucherId);
    }
}