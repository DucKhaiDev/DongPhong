package Controller.Client;

import Entity.Brand;
import Entity.Category;
import Services.deploy.BrandService;
import Services.deploy.CategoryService;
import Tools.ReleaseMemory;
import Util.Constant;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "WelcomeController", value = "/welcome")
public class WelcomeController extends HttpServlet {
    private CategoryService categoryService = new CategoryService();
    private BrandService brandService = new BrandService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReleaseMemory.deleteUnusedImg();

        HttpSession session = request.getSession();

        List<Category> lvrCategories = categoryService.getCategoryByRoom("LVR");
        session.setAttribute("lvrCategories", lvrCategories);
        List<Category> kitCategories = categoryService.getCategoryByRoom("KIT");
        session.setAttribute("kitCategories", kitCategories);
        List<Category> bedCategories = categoryService.getCategoryByRoom("BED");
        session.setAttribute("bedCategories", bedCategories);
        List<Category> offCategories = categoryService.getCategoryByRoom("OFF");
        session.setAttribute("offCategories", offCategories);
        List<Category> altCategories = categoryService.getCategoryByRoom("ALT");
        session.setAttribute("altCategories", altCategories);

        List<Brand> brands = brandService.getAll();
        session.setAttribute("brands", brands);

        request.getRequestDispatcher(Constant.Path.HOME).forward(request, response);
    }
}
