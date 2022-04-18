package Controller.Client;

import Entity.Voucher;
import Services.deploy.VoucherService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ApplyVoucher", value = "/apply-voucher")
public class ApplyVoucher extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String voucherId = request.getParameter("voucher");
        Voucher voucher = new VoucherService().getVoucher(voucherId);
        request.getSession().setAttribute("voucher", voucher);
        response.sendRedirect(request.getContextPath() + request.getParameter("forwardTo"));
    }
}
