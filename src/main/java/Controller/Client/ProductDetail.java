package Controller.Client;

import Entity.Product;
import Entity.Review;
import Entity.User;
import Util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductDetail", value = "/products/product-detail")
public class ProductDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("id");

        Product product = Constant.Service.PRODUCT_SERVICE.getProduct(productId);
        request.setAttribute("product", product);

        User user = (User) request.getSession().getAttribute("account");

        List<Review> reviews = Constant.Service.REVIEW_SERVICE.getAll(productId);
        request.setAttribute("reviews", reviews);

        boolean isRate = false;
        if (user != null) {
            isRate = Double.compare(Constant.Service.REVIEW_SERVICE.checkRateStatus(productId, user.getUsername()), 0) > 0;
        }
        request.setAttribute("isRate", isRate);

        //Related products
        List<Product> relatedProducts = Constant.Service.PRODUCT_SERVICE.getProductByCategory(product.getCategory().getCategoryId());
        request.setAttribute("relatedProducts", relatedProducts);

        request.getRequestDispatcher(Constant.Path.PRODUCT_DETAIL).forward(request, response);
    }
}