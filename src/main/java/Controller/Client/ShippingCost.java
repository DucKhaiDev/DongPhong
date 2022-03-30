package Controller.Client;

import Services.deploy.ProvinceService;
import Tools.CalculateShipping;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "ShippingCost", value = "/shipping-cost")
public class ShippingCost extends HttpServlet {
    private final ProvinceService provinceService = new ProvinceService();

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
        response.sendRedirect(request.getContextPath() + "/cart");
    }
}
