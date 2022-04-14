package Controller.Admin;

import Services.deploy.CartItemService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "OrderRemoveItem", value = "/admin/order/edit/removeItem")
public class OrderRemoveItem extends HttpServlet {
    private final CartItemService cartItemService = new CartItemService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cartItemId = Integer.parseInt(request.getParameter("id"));
        cartItemService.delete(cartItemId);
        String forwardTo = request.getParameter("forwardTo");
        response.sendRedirect(forwardTo);
    }
}
