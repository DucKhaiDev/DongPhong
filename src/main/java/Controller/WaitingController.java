package Controller;

import Entity.*;
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
    private final CartItemService cartItemService = new CartItemService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        //Use the account stored in cookie
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        if (username != null && password != null) {
            User user = userService.login(username, password);
            session.setAttribute("account", user);
        }

        if (session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");
            request.setAttribute("username", user.getUsername());

            //displayName
            String displayName = user.getUsername();
            String firstName = user.getFirstName();
            String lastName = user.getLastName();

            if (lastName != null && !lastName.trim().equals("")) {
                displayName = lastName;

                if (firstName != null && !firstName.trim().equals("")) {
                    displayName += " " + firstName;
                }
            }
            session.setAttribute("displayName", displayName);

            //wishlist
            WishList wishList = wishListService.getWishListByUser(user.getUserId());
            session.setAttribute("wishList", wishList);

            //wishlist items
            List<WLItem> wlItems = wlItemService.getItemByWishList(wishList.getWishListId());
            session.setAttribute("wlItems", wlItems);

            //cart
            Cart cart = cartService.getLastCart(user.getUserId());
            session.setAttribute("cart", cart);

            //cart items
            List<CartItem> cartItems = cartItemService.getItemByCart(cart.getCartId());
            session.setAttribute("cartItems", cartItems);

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
