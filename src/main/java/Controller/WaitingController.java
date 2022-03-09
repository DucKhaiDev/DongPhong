package Controller;

import Entity.Cart;
import Entity.User;
import Entity.WLItem;
import Entity.WishList;
import Services.deploy.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "WaitingController", value = "/waiting")
public class WaitingController extends HttpServlet {
    private final UserService userService = new UserService();
    private final WishListService wishListService = new WishListService();
    private final WLItemService wlItemService = new WLItemService();
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

            //Danh sách yêu thích
            WishList wishList = wishListService.getWishListByUser(user.getUserId());
            session.setAttribute("wishList", wishList);

            //Danh sách yêu thích item
            List<WLItem> wlItems = wlItemService.getItemByWishList(wishList.getWishListId());
            session.setAttribute("wlItems", wlItems);

            //Giỏ hàng
            Cart cart = cartService.getCartByUser(user.getUserId());
            session.setAttribute("cart", cart);

            //Giỏ hàng item


            if (session.getAttribute("forwardTo") != null) {
                response.sendRedirect(session.getAttribute("forwardTo").toString());
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
