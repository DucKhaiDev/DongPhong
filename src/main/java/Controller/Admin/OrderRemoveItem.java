package Controller.Admin;

import Util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "OrderRemoveItem", value = "/admin/order/edit/removeItem")
public class OrderRemoveItem extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Constant.Service.CART_ITEM_SERVICE.delete(Integer.parseInt(request.getParameter("id")));
        response.sendRedirect(request.getParameter("forwardTo"));
    }
}