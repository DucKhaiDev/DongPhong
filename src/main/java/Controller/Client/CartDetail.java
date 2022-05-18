package Controller.Client;

import Entity.Cart;
import Entity.CartItem;
import Entity.User;
import Entity.Voucher;
import Util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet(name = "CartDetail", value = "/cart")
public class CartDetail extends HttpServlet {
    private HttpSession session;
    private String cartId;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!LoginController.checkLogin(request, response)) {
            return;
        }

        session = request.getSession();
        cartId = ((Cart) session.getAttribute("cart")).getCartId();
        List<Voucher> availableVoucher = Constant.Service.VOUCHER_SERVICE.getAvailableVoucher(cartId);
        if (!((User) session.getAttribute("account")).isVc_chaomung()) {
            availableVoucher.add(Constant.Service.VOUCHER_SERVICE.getVoucher("CHAOMUNG"));
        }
        request.setAttribute("availableVoucher", availableVoucher);
        request.setAttribute("selected", 26);
        request.getRequestDispatcher(Constant.Path.CART).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cartItemId = Integer.parseInt(request.getParameter("cartItemId"));
        CartItem item = Constant.Service.CART_ITEM_SERVICE.getCartItem(cartItemId);

        int numProduct = Integer.parseInt(request.getParameter("numProduct"));
        BigDecimal productPrice = item.getProduct().getProductPrice();
        BigDecimal value = productPrice.multiply(new BigDecimal(numProduct));

        item.setQuantity(numProduct);
        item.setValue(value);

        Constant.Service.CART_ITEM_SERVICE.edit(item);

        //Update cart
        List<CartItem> cartItems = Constant.Service.CART_ITEM_SERVICE.getItemByCart(cartId);
        session.setAttribute("cartItems", cartItems);

        response.sendRedirect(request.getContextPath() + "/cart");
    }
}