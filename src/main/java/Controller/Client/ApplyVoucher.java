package Controller.Client;

import Util.Constant;
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
        request.getSession().setAttribute("voucher", Constant.Service.VOUCHER_SERVICE.getVoucher(voucherId));
        response.sendRedirect(request.getContextPath() + request.getParameter("forwardTo"));
    }
}