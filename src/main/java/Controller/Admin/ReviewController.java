package Controller.Admin;

import Entity.Product;
import Services.deploy.ReviewService;
import Util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ReviewController", value = "/admin/review")
public class ReviewController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = new ReviewService().getAllProducts();
        request.setAttribute("products", products);
        request.getRequestDispatcher(Constant.Path.ADMIN_REVIEW).forward(request, response);
    }
}
