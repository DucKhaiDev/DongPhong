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
import java.sql.Timestamp;

@WebServlet(name = "AddReview", value = "/add-review")
public class AddReview extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("account");

        String productId = request.getParameter("productId");
        Product product = Constant.Service.PRODUCT_SERVICE.getProduct(productId);

        double reviewRate = Double.parseDouble(request.getParameter("rating"));
        boolean isFirst = true;

        if (Double.compare(Constant.Service.REVIEW_SERVICE.checkRateStatus(productId, user.getUsername()), 0) == 1) {
            reviewRate = Constant.Service.REVIEW_SERVICE.checkRateStatus(productId, user.getUsername());
            isFirst = false;
        }

        String reviewContent = request.getParameter("review");
        Timestamp reviewDate = new Timestamp(System.currentTimeMillis());
        boolean isRate = false;

        if (Double.compare(reviewRate, 0) == 1) {
            isRate = true;
            Constant.Service.REVIEW_SERVICE.syncRate(productId, user.getUsername(), reviewRate);
            if (isFirst) {
                double rate = product.getProductRate();
                if (Double.compare(rate, 0) == 0) {
                    product.setProductRate(reviewRate);
                } else {
                    product.setProductRate((product.getProductRate() + reviewRate) / 2);
                }

                Constant.Service.PRODUCT_SERVICE.edit(product);
            }
        }

        Constant.Service.REVIEW_SERVICE.insert(new Review(user, product, reviewRate, reviewContent, reviewDate, isRate));

        response.sendRedirect(request.getContextPath() + "/products/product-detail?id=" + productId);
    }
}