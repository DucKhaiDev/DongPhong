package Controller.Admin;

import Entity.Brand;
import Entity.Category;
import Services.deploy.BrandService;
import Services.deploy.CategoryService;
import Util.Constant;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddProductController", value = "/admin/product/add")
public class AddProductController extends HttpServlet {
    static CategoryService categoryService = new CategoryService();
    static BrandService brandService = new BrandService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = categoryService.getAll();
        request.setAttribute("categories", categories);
        List<Brand> brands = brandService.getAll();
        request.setAttribute("brands", brands);
        request.getRequestDispatcher(Constant.Path.ADMIN_ADD_PRODUCT).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
