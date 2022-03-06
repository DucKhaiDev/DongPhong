package Controller.Admin;

import Services.deploy.CategoryService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DeleteCategoryController", value = "/admin/category/delete")
public class DeleteCategoryController extends HttpServlet {
    private final CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        categoryService.delete(request.getParameter("id"));
        response.sendRedirect(request.getContextPath() + "/admin/category");
    }
}
