package Controller.Admin;

import Entity.Product;
import Entity.Review;
import Services.deploy.ProductService;
import Services.deploy.ReviewService;
import Util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ReviewDetail", value = "/admin/review/review-detail")
public class ReviewDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("id");
        Product product = new ProductService().getProduct(productId);
        request.setAttribute("product", product);
        List<Review> reviews = new ReviewService().getAll(productId);
        request.setAttribute("reviews", reviews);
        request.getRequestDispatcher(Constant.Path.ADMIN_REVIEW_DETAIL).forward(request, response);
    }
}
