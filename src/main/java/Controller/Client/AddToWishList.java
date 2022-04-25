package Controller.Client;

import Entity.Product;
import Entity.User;
import Entity.WLItem;
import Entity.WishList;
import Util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "AddToWishList", value = "/wishlist/add")
public class AddToWishList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forwardTo = request.getParameter("forwardTo");

        //Check login
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");
        if (user == null) {
            session.setAttribute("forwardTo", forwardTo);
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String productId = request.getParameter("id");
        Product product = Constant.Service.PRODUCT_SERVICE.getProduct(productId);
        WishList wishList = (WishList) session.getAttribute("wishList");

        //Add the product if it doesn't exist in the wishList
        if (!Constant.Service.WL_ITEM_SERVICE.checkExistItem(productId, wishList.getWishListId())) {
            //Add product to wishList
            Constant.Service.WL_ITEM_SERVICE.insert(new WLItem(product, wishList));

            //Update wishlist items
            session.setAttribute("wlItems", Constant.Service.WL_ITEM_SERVICE.getItemByWishList(wishList.getWishListId()));
        }

        response.sendRedirect(forwardTo);
    }
}