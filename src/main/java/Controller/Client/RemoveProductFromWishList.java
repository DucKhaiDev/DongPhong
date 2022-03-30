package Controller.Client;

import Entity.WLItem;
import Services.deploy.WLItemService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "RemoveProductFromWishList", value = "/wishlist/remove")
public class RemoveProductFromWishList extends HttpServlet {
    private final WLItemService wlItemService = new WLItemService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int wlItemId = Integer.parseInt(request.getParameter("id"));
        String wishListId = wlItemService.getWLItem(wlItemId).getWishList().getWishListId();
        wlItemService.delete(wlItemId);

        //Update wishlist items
        HttpSession session = request.getSession();
        List<WLItem> wlItems = wlItemService.getItemByWishList(wishListId);
        session.setAttribute("wlItems", wlItems);

        String forwardTo = request.getParameter("forwardTo");
        response.sendRedirect(forwardTo);
    }
}
