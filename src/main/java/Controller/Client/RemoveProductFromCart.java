package Controller.Client;

import Entity.Cart;
import Util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "RemoveProductFromCart", value = "/cart/remove")
public class RemoveProductFromCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (LoginController.checkLogin(request, response)) {
            return;
        }

        int cartItemId = Integer.parseInt(request.getParameter("id"));
        String cartId = Constant.Service.CART_ITEM_SERVICE.getCartItem(cartItemId).getCart().getCartId();

        //Check account
        HttpSession session = request.getSession();
        Cart sysCart = (Cart) session.getAttribute("cart");
        if (sysCart.getCartId().equals(cartId)) {
            Constant.Service.CART_ITEM_SERVICE.delete(cartItemId);
        }

        //Update cart items
        session.setAttribute("cartItems", Constant.Service.CART_ITEM_SERVICE.getItemByCart(cartId));

        response.sendRedirect(request.getParameter("forwardTo"));
    }
}