package Controller.Client;

import Entity.*;
import Tools.ReleaseMemory;
import Util.Constant;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Welcome", value = "/welcome")
public class Welcome extends HttpServlet {
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
        context.setAttribute("lvrCategories", Constant.Service.CATEGORY_SERVICE.getCategoryByRoom("LVR"));
        context.setAttribute("kitCategories", Constant.Service.CATEGORY_SERVICE.getCategoryByRoom("KIT"));
        context.setAttribute("bedCategories", Constant.Service.CATEGORY_SERVICE.getCategoryByRoom("BED"));
        context.setAttribute("offCategories", Constant.Service.CATEGORY_SERVICE.getCategoryByRoom("OFF"));
        context.setAttribute("altCategories", Constant.Service.CATEGORY_SERVICE.getCategoryByRoom("ALT"));
        context.setAttribute("rooms", Constant.Service.ROOM_SERVICE.getAll());
        context.setAttribute("brands", Constant.Service.BRAND_SERVICE.getAll());

        request.setAttribute("products", Constant.Service.PRODUCT_SERVICE.getAll());
        request.setAttribute("productLvr", Constant.Service.PRODUCT_SERVICE.countProduct("LVR"));
        request.setAttribute("productKit", Constant.Service.PRODUCT_SERVICE.countProduct("KIT"));
        request.setAttribute("productBed", Constant.Service.PRODUCT_SERVICE.countProduct("BED"));
        request.setAttribute("productOff", Constant.Service.PRODUCT_SERVICE.countProduct("OFF"));
        request.setAttribute("productAlt", Constant.Service.PRODUCT_SERVICE.countProduct("ALT"));

        request.getRequestDispatcher(Constant.Path.HOME).forward(request, response);
    }

    private synchronized List<User> getUsers() {
        return Constant.Service.USER_SERVICE.getAll();
    }

    private synchronized List<ProImage> getImages() {
        return Constant.Service.PRO_IMAGE_SERVICE.getAll();
    }
}
