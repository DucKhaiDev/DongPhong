package Controller.Client;

import Entity.*;
import Services.deploy.*;
import Tools.ReleaseMemory;
import Util.Constant;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Welcome", value = "/welcome")
public class Welcome extends HttpServlet {
    private final CategoryService categoryService = new CategoryService();
    private final BrandService brandService = new BrandService();
    private final UserService userService = new UserService();
    private final ProImageService imageService = new ProImageService();
    private final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Synchronized
        getUsers();
        getImages();

        Thread deleteUnusedImg = new Thread(() -> {
            List<User> users = getUsers();
            List<ProImage> images = getImages();
            ReleaseMemory.deleteUnusedImg(users, images);
        });
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

        List<Room> rooms = new RoomService().getAll();
        context.setAttribute("rooms", rooms);

        List<Brand> brands = brandService.getAll();
        context.setAttribute("brands", brands);

        List<Product> products = productService.getAll();
        request.setAttribute("products", products);
        request.setAttribute("productLvr", productService.countProduct("LVR"));
        request.setAttribute("productKit", productService.countProduct("KIT"));
        request.setAttribute("productBed", productService.countProduct("BED"));
        request.setAttribute("productOff", productService.countProduct("OFF"));
        request.setAttribute("productAlt", productService.countProduct("ALT"));
        request.getRequestDispatcher(Constant.Path.HOME).forward(request, response);
    }

    private synchronized List<User> getUsers() {
        return userService.getAll();
    }

    private synchronized List<ProImage> getImages() {
        return imageService.getAll();
    }
}
