package Controller.Client;

import Entity.Voucher;
import Services.deploy.VoucherService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "AddVoucher", value = "/add-voucher")
public class AddVoucher extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String voucherId = request.getParameter("voucher");
        Voucher voucher = new VoucherService().getVoucher(voucherId);
        request.getSession().setAttribute("discount", voucher.getDiscount());
        response.sendRedirect(request.getContextPath() + request.getParameter("forwardTo"));
    }
}
