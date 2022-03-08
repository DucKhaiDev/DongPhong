package Controller.Client;

import Entity.Product;
import Entity.WLItem;
import Entity.WishList;
import Services.deploy.ProductService;
import Services.deploy.WLItemService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "AddToWishListController", value = "/wishlist/add")
public class AddToWishListController extends HttpServlet {
    private final ProductService productService = new ProductService();
    private final WLItemService wlItemService = new WLItemService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forwardTo = getUrl(request);

        //Kiểm tra đăng nhập
        HttpSession session = request.getSession();
        if (session.getAttribute("account") == null) {
            session.setAttribute("forwardTo_1", forwardTo);
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        //Thêm sản phẩm vào Danh sách yêu thích
        String productId = request.getParameter("productId");
        Product product = productService.getProduct(productId);
        WishList wishList = (WishList) session.getAttribute("wishList");
        wlItemService.insert(new WLItem(product, wishList));

        response.sendRedirect(forwardTo);
    }

    private String getUrl(HttpServletRequest request) {
        String productListByCategory = request.getParameter("productListByCategory");

        String idParam = request.getParameter("idParam");
        if (!idParam.isEmpty()) {
            idParam = "?id=" + idParam;
        }

        String searchParam = request.getParameter("searchParam");
        if (!searchParam.isEmpty()) {
            searchParam = "&search=" + searchParam;
        }

        String sortByParam = request.getParameter("sortByParam");
        if (!sortByParam.isEmpty()) {
            sortByParam = "&sortBy=" + sortByParam;
        }

        String brandParam = request.getParameter("brandParam");
        if (!brandParam.isEmpty()) {
            brandParam = "&brand=" + brandParam;
        }

        String priceParam = request.getParameter("priceParam");
        if (!priceParam.isEmpty()) {
            priceParam = "&price=" + priceParam;
        }

        String ratingParam = request.getParameter("ratingParam");
        if (!ratingParam.isEmpty()) {
            ratingParam = "&rating=" + ratingParam;
        }

        return productListByCategory + idParam + searchParam + sortByParam + brandParam + priceParam + ratingParam;
    }
}
