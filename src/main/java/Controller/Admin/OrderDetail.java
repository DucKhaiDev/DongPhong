package Controller.Admin;

import Entity.Cart;
import Entity.CartItem;
import Entity.Order;
import Util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderDetail", value = "/admin/order/detail")
public class OrderDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("id"));

        Order order = Constant.Service.ORDER_SERVICE.getOrder(orderId);
        request.setAttribute("order", order);

        Cart cart = order.getCart();
        List<CartItem> cartItems = Constant.Service.CART_ITEM_SERVICE.getItemByCart(cart.getCartId());
        request.setAttribute("cartItems", cartItems);

        request.getRequestDispatcher(Constant.Path.ADMIN_ORDER_DETAIL).forward(request, response);
    }
}