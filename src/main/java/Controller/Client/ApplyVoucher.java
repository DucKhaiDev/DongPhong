package Controller.Client;

import Util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "ApplyVoucher", value = "/apply-voucher")
public class ApplyVoucher extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (LoginController.checkLogin(request, response)) {
            return;
        }

        HttpSession session = request.getSession();

        String voucherId = request.getParameter("voucher");
        session.setAttribute("voucher", Constant.Service.VOUCHER_SERVICE.getVoucher(voucherId));

        //Order info
        String ord_recipientName = request.getParameter("signRecipientName");
        session.setAttribute("ord_recipientName", ord_recipientName);
        String ord_recipientPhone = request.getParameter("signRecipientPhone");
        session.setAttribute("ord_recipientPhone", ord_recipientPhone);

        response.sendRedirect(request.getContextPath() + request.getParameter("forwardTo"));
    }
}