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
        HttpSession session = request.getSession();
        String province = request.getParameter("province");
        session.setAttribute("selectedProvince", province);
        BigDecimal shippingCost = CalculateShipping.calculateShipping(Integer.parseInt(province));
        session.setAttribute("shippingCost", shippingCost);
        String district = request.getParameter("district");
        session.setAttribute("selectedDistrict", district);
        String ward = request.getParameter("ward");
        session.setAttribute("selectedWard", ward);
        String recaddress = request.getParameter("recaddress");
        session.setAttribute("recaddress", recaddress);

        String orderAccount = request.getParameter("signOrderAccount");
        session.setAttribute("orderAccount", orderAccount);

        String ord_recipientName = request.getParameter("signRecipientName");
        session.setAttribute("ord_recipientName", ord_recipientName);

        String ord_recipientPhone = request.getParameter("signRecipientPhone");
        session.setAttribute("ord_recipientPhone", ord_recipientPhone);

        String recAddress = request.getParameter("recaddress") + ", "
                + request.getParameter("selectedWard") + ", "
                + request.getParameter("selectedDistrict") + ", "
                + request.getParameter("selectedProvince") + ".";
        session.setAttribute("recipientAddress", recAddress);

        response.sendRedirect(request.getContextPath() + request.getParameter("forwardTo"));
    }
}
