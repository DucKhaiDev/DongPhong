package Controller.Client;

import Tools.CalculateShipping;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "ShippingCost", value = "/shipping-cost")
public class ShippingCost extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!LoginController.checkLogin(request, response)) {
            return;
        }

        String province = request.getParameter("province");
        BigDecimal shippingCost = CalculateShipping.calculateShipping(Integer.parseInt(province));
        String district = request.getParameter("district");
        String ward = request.getParameter("ward");
        String recaddress = request.getParameter("recaddress");
        String ord_recipientName = request.getParameter("signRecipientName");
        String ord_recipientPhone = request.getParameter("signRecipientPhone");
        String recipientAddress = request.getParameter("recaddress") + ", "
                + request.getParameter("selectedWard") + ", "
                + request.getParameter("selectedDistrict") + ", "
                + request.getParameter("selectedProvince") + ".";

        HttpSession session = request.getSession();
        session.setAttribute("selectedProvince", province);
        session.setAttribute("shippingCost", shippingCost);
        session.setAttribute("selectedDistrict", district);
        session.setAttribute("selectedWard", ward);
        session.setAttribute("recaddress", recaddress);
        session.setAttribute("ord_recipientName", ord_recipientName);
        session.setAttribute("ord_recipientPhone", ord_recipientPhone);
        session.setAttribute("recipientAddress", recipientAddress);

        response.sendRedirect(request.getContextPath() + request.getParameter("forwardTo"));
    }
}