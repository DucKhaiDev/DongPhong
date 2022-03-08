package Controller.Client;

import Entity.Brand;
import Entity.Category;
import Entity.ProImage;
import Entity.User;
import Services.deploy.BrandService;
import Services.deploy.CategoryService;
import Services.deploy.ProImageService;
import Services.deploy.UserService;
import Tools.ReleaseMemory;
import Util.Constant;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "WelcomeController", value = "/welcome")
public class WelcomeController extends HttpServlet {
    private final CategoryService categoryService = new CategoryService();
    private final BrandService brandService = new BrandService();
    private final UserService userService = new UserService();
    private final ProImageService imageService = new ProImageService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Đồng bộ dữ liệu đa luồng
        List<User> users = userService.getAll();
        List<ProImage> images = imageService.getAll();

        Thread deleteUnusedImg = new Thread(() -> ReleaseMemory.deleteUnusedImg(users, images));
        deleteUnusedImg.start();

        ServletContext context = request.getServletContext();

        List<Category> lvrCategories = categoryService.getCategoryByRoom("LVR");
        context.setAttribute("lvrCategories", lvrCategories);
        List<Category> kitCategories = categoryService.getCategoryByRoom("KIT");
        context.setAttribute("kitCategories", kitCategories);
        List<Category> bedCategories = categoryService.getCategoryByRoom("BED");
        context.setAttribute("bedCategories", bedCategories);
        List<Category> offCategories = categoryService.getCategoryByRoom("OFF");
        context.setAttribute("offCategories", offCategories);
        List<Category> altCategories = categoryService.getCategoryByRoom("ALT");
        context.setAttribute("altCategories", altCategories);

        List<Brand> brands = brandService.getAll();
        context.setAttribute("brands", brands);

        request.getRequestDispatcher(Constant.Path.HOME).forward(request, response);
    }
}
