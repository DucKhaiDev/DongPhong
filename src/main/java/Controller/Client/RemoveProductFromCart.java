package Controller.Client;

import Util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "RemoveProductFromCart", value = "/cart/remove")
public class RemoveProductFromCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cartItemId = Integer.parseInt(request.getParameter("id"));
        String cartId = Constant.Service.CART_ITEM_SERVICE.getCartItem(cartItemId).getCart().getCartId();
        Constant.Service.CART_ITEM_SERVICE.delete(cartItemId);

        //Update cart items
        request.getSession().setAttribute("cartItems", Constant.Service.CART_ITEM_SERVICE.getItemByCart(cartId));

        response.sendRedirect(request.getParameter("forwardTo"));
    }
}