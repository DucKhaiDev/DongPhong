package Controller.Client;

import Util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "RemoveProductFromWishList", value = "/wishlist/remove")
public class RemoveProductFromWishList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int wlItemId = Integer.parseInt(request.getParameter("id"));
        String wishListId = Constant.Service.WL_ITEM_SERVICE.getWLItem(wlItemId).getWishList().getWishListId();
        Constant.Service.WL_ITEM_SERVICE.delete(wlItemId);

        //Update wishlist items
        request.getSession().setAttribute("wlItems", Constant.Service.WL_ITEM_SERVICE.getItemByWishList(wishListId));

        response.sendRedirect(request.getParameter("forwardTo"));
    }
}
