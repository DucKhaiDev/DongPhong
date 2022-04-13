package Controller.Admin;

import Entity.Cart;
import Entity.CartItem;
import Entity.Order;
import Services.deploy.CartItemService;
import Services.deploy.OrderService;
import Util.Constant;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderDetail", value = "/admin/order/detail")
public class OrderDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("id"));
        Order order = new OrderService().getOrder(orderId);
        request.setAttribute("order", order);

        Cart cart = order.getCart();
        List<CartItem> cartItems = new CartItemService().getItemByCart(cart.getCartId());
        request.setAttribute("cartItems", cartItems);

        request.getRequestDispatcher(Constant.Path.ADMIN_ORDER_DETAIL).forward(request, response);
    }
}
