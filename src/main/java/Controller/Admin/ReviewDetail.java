package Controller.Admin;

import Controller.Client.LoginController;
import Util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ReviewDetail", value = "/admin/review/review-detail")
public class ReviewDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (LoginController.checkLogin(request, response)) {
            return;
        }

        String productId = request.getParameter("id");
        request.setAttribute("product", Constant.Service.PRODUCT_SERVICE.getProduct(productId));
        request.setAttribute("reviews", Constant.Service.REVIEW_SERVICE.getAll(productId));
        request.getRequestDispatcher(Constant.Path.ADMIN_REVIEW_DETAIL).forward(request, response);
    }
}