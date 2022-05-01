package Controller;

import Entity.Cart;
import Entity.CartItem;
import Entity.User;
import Entity.WishList;
import Util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.TreeSet;

@WebServlet(name = "WaitingController", value = "/waiting")
public class WaitingController extends HttpServlet {
    public static String displayName(User user) {
        String displayName = user.getUsername();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();

        if (lastName != null && !lastName.trim().equals("")) {
            displayName = lastName;

            if (firstName != null && !firstName.trim().equals("")) {
                displayName += " " + firstName;
            }
        }

        return displayName;
    }

    public static void removeAllAttr(HttpSession session) {
        TreeSet<String> attributes = new TreeSet<>();
        Enumeration<String> enumeration = session.getAttributeNames();

        while (enumeration.hasMoreElements()) {
            attributes.add(enumeration.nextElement());
        }

        for (String attribute : attributes) {
            switch (attribute) {
                case "account":
                case "displayName":
                case "wishList":
                case "wlItems":
                case "cart":
                case "cartItems":
                    continue;
            }

            session.removeAttribute(attribute);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        //Use the account stored in cookie
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        if (username != null && password != null) {
            User user = Constant.Service.USER_SERVICE.login(username, password);
            session.setAttribute("account", user);
        }

        if (session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");
            request.setAttribute("username", user.getUsername());

            //displayName
            session.setAttribute("displayName", WaitingController.displayName(user));

            //wishlist
            WishList wishList = Constant.Service.WISH_LIST_SERVICE.getWishListByUser(user.getUserId());
            session.setAttribute("wishList", wishList);

            //wishlist items
            session.setAttribute("wlItems", Constant.Service.WL_ITEM_SERVICE.getItemByWishList(wishList.getWishListId()));

            //cart
            Cart cart = Constant.Service.CART_SERVICE.getLastCart(user.getUserId());
            session.setAttribute("cart", cart);

            //cart items
            List<CartItem> cartItems = Constant.Service.CART_ITEM_SERVICE.getItemByCart(cart.getCartId());
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