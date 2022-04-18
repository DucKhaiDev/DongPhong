package Controller.Admin;

import Services.deploy.ReviewService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DeleteReview", value = "/admin/review/delete")
public class DeleteReview extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int reviewId = Integer.parseInt(request.getParameter("id"));
        new ReviewService().delete(reviewId);
        response.sendRedirect(request.getContextPath() + "/admin/review/review-detail?id=" + request.getParameter("productId"));
    }
}
