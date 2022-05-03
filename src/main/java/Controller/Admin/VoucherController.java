package Controller.Admin;

import Controller.Client.LoginController;
import Util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "VoucherController", value = "/admin/voucher")
public class VoucherController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (LoginController.checkLoginAdmin(request, response)) {
            return;
        }

        request.setAttribute("vouchers", Constant.Service.VOUCHER_SERVICE.getAll());
        request.getRequestDispatcher(Constant.Path.ADMIN_VOUCHER).forward(request, response);
    }
}