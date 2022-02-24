package Controller.Admin;

import Entity.Category;
import Services.deploy.CategoryService;
import Util.Constant;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "AddCategoryController", value = "/admin/category/add")
public class AddCategoryController extends HttpServlet {
    private CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Constant.Path.ADMIN_ADD_CATEGORY).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String CAT_ID = request.getParameter("cat_id");
        if (categoryService.checkExistID(CAT_ID)) {
            String existID = "Mã loại sản phẩm đã tồn tại!";
            request.setAttribute("existID", existID);
            request.getRequestDispatcher(Constant.Path.ADMIN_ADD_CATEGORY).forward(request, response);
            return;
        }

        String CAT_NAME = request.getParameter("cat_name");
        String CAT_DES = request.getParameter("cat_des");
        Category category = new Category(CAT_ID, CAT_NAME, CAT_DES);
        categoryService.insert(category);
        response.sendRedirect(request.getContextPath() + "/admin/category");
    }
}
