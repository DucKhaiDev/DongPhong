package Controller.Client;

import Entity.CartItem;
import Services.deploy.CartItemService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "RemoveProductFromCart", value = "/cart/remove")
public class RemoveProductFromCart extends HttpServlet {
    private final static CartItemService cartItemService = new CartItemService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cartItemId = Integer.parseInt(request.getParameter("id"));
        String cartId = cartItemService.getCartItem(cartItemId).getCart().getCartId();
        cartItemService.delete(cartItemId);

        //Cập nhật giỏ hàng
        HttpSession session = request.getSession();
        List<CartItem> cartItems = cartItemService.getItemByCart(cartId);
        session.setAttribute("cartItems", cartItems);

        String forwardTo = request.getParameter("forwardTo");
        response.sendRedirect(forwardTo);
    }
}
