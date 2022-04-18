package Controller.Client;

import Entity.Product;
import Entity.Review;
import Entity.User;
import Services.deploy.ProductService;
import Services.deploy.ReviewService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Timestamp;

@WebServlet(name = "AddReview", value = "/add-review")
public class AddReview extends HttpServlet {
    private final ReviewService reviewService = new ReviewService();
    private final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("account");
        String productId = request.getParameter("productId");
        Product product = productService.getProduct(productId);
        double reviewRate = Double.parseDouble(request.getParameter("rating"));
        boolean isFirst = true;
        if (Double.compare(reviewService.checkRateStatus(productId, user.getUsername()), 0) == 1) {
            reviewRate = reviewService.checkRateStatus(productId, user.getUsername());
            isFirst = false;
        }
        String reviewContent = request.getParameter("review");
        Timestamp reviewDate = new Timestamp(System.currentTimeMillis());
        boolean isRate = false;
        if (Double.compare(reviewRate, 0) == 1) {
            isRate = true;
            reviewService.syncRate(productId, user.getUsername(), reviewRate);
            if (isFirst) {
                product.setProductRate((product.getProductRate() + reviewRate)/2);
                productService.edit(product);
            }
        }
        Review review = new Review(user, product, reviewRate, reviewContent, reviewDate, isRate);
        reviewService.insert(review);
        response.sendRedirect(request.getContextPath() + "/products/product-detail?id=" + productId);
    }
}
