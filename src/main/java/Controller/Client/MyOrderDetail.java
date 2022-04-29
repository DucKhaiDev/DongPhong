package Controller.Client;

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

@WebServlet(name = "MyOrderDetail", value = "/member/my-order/detail")
public class MyOrderDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order myOrder = Constant.Service.ORDER_SERVICE.getOrder(Integer.parseInt(request.getParameter("id")));
        List<CartItem> cartItems = Constant.Service.CART_ITEM_SERVICE.getItemByCart(myOrder.getCart().getCartId());
        request.setAttribute("myOrder", myOrder);
        request.setAttribute("cartItems", cartItems);
        request.getRequestDispatcher(Constant.Path.MY_ORDER_DETAIL).forward(request, response);
    }
}