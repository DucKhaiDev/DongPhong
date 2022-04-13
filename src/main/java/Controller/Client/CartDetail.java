package Controller.Client;

import Entity.Cart;
import Entity.CartItem;
import Services.deploy.CartItemService;
import Util.Constant;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet(name = "CartDetail", value = "/cart")
public class CartDetail extends HttpServlet {
    private final CartItemService cartItemService = new CartItemService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int selected = 26;
        request.setAttribute("selected", selected);
        request.getRequestDispatcher(Constant.Path.CART).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cartItemId = Integer.parseInt(request.getParameter("cartItemId"));
        int numProduct = Integer.parseInt(request.getParameter("numProduct"));
        CartItem item = cartItemService.getCartItem(cartItemId);
        item.setQuantity(numProduct);
        BigDecimal productPrice = item.getProduct().getProductPrice();
        BigDecimal value = productPrice.multiply(new BigDecimal(numProduct));
        item.setValue(value);
        cartItemService.edit(item);

        //Update cart
        HttpSession session = request.getSession();
        String cartId = ((Cart) session.getAttribute("cart")).getCartId();
        List<CartItem> cartItems = cartItemService.getItemByCart(cartId);
        session.setAttribute("cartItems", cartItems);

        response.sendRedirect(request.getContextPath() + "/cart");
    }
}
