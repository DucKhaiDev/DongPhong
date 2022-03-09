package Controller.Client;

import Entity.Product;
import Entity.User;
import Entity.WLItem;
import Entity.WishList;
import Services.deploy.ProductService;
import Services.deploy.WLItemService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddToWishList", value = "/wishlist/add")
public class AddToWishList extends HttpServlet {
    private final ProductService productService = new ProductService();
    private final WLItemService wlItemService = new WLItemService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forwardTo = request.getParameter("forwardTo");

        //Kiểm tra đăng nhập
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");
        if (user == null) {
            session.setAttribute("forwardTo", forwardTo);
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String productId = request.getParameter("id");
        Product product = productService.getProduct(productId);
        WishList wishList = (WishList) session.getAttribute("wishList");

        //Thêm sản phẩm nếu sản phẩm chưa tồn tại trong danh sách yêu thích
        if (!wlItemService.checkExistItem(productId, wishList.getWishListId())) {
            //Thêm sản phẩm vào Danh sách yêu thích
            wlItemService.insert(new WLItem(product, wishList));

            //Cập nhật wlItems
            List<WLItem> wlItems = wlItemService.getItemByWishList(wishList.getWishListId());
            session.setAttribute("wlItems", wlItems);
        }

        response.sendRedirect(forwardTo);
    }
}
