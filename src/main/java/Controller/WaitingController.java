package Controller;

import Entity.Cart;
import Entity.User;
import Entity.WishList;
import Services.deploy.CartService;
import Services.deploy.UserService;
import Services.deploy.WishListService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "WaitingController", value = "/waiting")
public class WaitingController extends HttpServlet {
    private final UserService userService = new UserService();
    private final WishListService wishListService = new WishListService();
    private final CartService cartService = new CartService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        //Sử dụng tài khoản đã lưu trong cookie
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        if (username != null && password != null) {
            User user = userService.login(username, password);
            session.setAttribute("account", user);
        }

        if (session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");
            request.setAttribute("username", user.getUsername());

            WishList wishList = wishListService.getWishListByUser(user.getUserId());
            session.setAttribute("wishList", wishList);

            Cart cart = cartService.getCartByUser(user.getUserId());
            session.setAttribute("cart", cart);

            if (session.getAttribute("forwardTo_1") != null) {
                response.sendRedirect(session.getAttribute("forwardTo_1").toString());
            } else if (user.getRole()) {
                response.sendRedirect(request.getContextPath() + "/welcome");
            } else {
                response.sendRedirect(request.getContextPath() + "/admin");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}
