package Controller.Admin;

import Entity.Voucher;
import Services.deploy.VoucherService;
import Util.Constant;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "VoucherController", value = "/admin/voucher")
public class VoucherController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Voucher> vouchers = new VoucherService().getAll();
        request.setAttribute("vouchers", vouchers);
        request.getRequestDispatcher(Constant.Path.ADMIN_VOUCHER).forward(request, response);
    }
}
