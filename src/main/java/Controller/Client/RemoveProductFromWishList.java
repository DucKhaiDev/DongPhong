package Controller.Client;

import Entity.WishList;
import Util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "RemoveProductFromWishList", value = "/wishlist/remove")
public class RemoveProductFromWishList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!LoginController.checkLogin(request, response)) {
            return;
        }

        int wlItemId = Integer.parseInt(request.getParameter("id"));
        String wishListId = Constant.Service.WL_ITEM_SERVICE.getWLItem(wlItemId).getWishList().getWishListId();

        //Check account
        HttpSession session = request.getSession();
        WishList sysWishList = (WishList) session.getAttribute("wishList");
        if (sysWishList.getWishListId().equals(wishListId)) {
            Constant.Service.WL_ITEM_SERVICE.delete(wlItemId);
        }

        //Update wishlist items
        session.setAttribute("wlItems", Constant.Service.WL_ITEM_SERVICE.getItemByWishList(wishListId));

        response.sendRedirect(request.getParameter("forwardTo"));
    }
}