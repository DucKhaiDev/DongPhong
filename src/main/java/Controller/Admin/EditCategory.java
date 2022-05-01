package Controller.Admin;

import Controller.Client.LoginController;
import Entity.Category;
import Entity.Room;
import Util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "EditCategory", value = "/admin/category/edit")
public class EditCategory extends HttpServlet {
    private Category category;
    private String categoryId;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (LoginController.checkLogin(request, response)) {
            return;
        }

        categoryId = request.getParameter("id");

        category = Constant.Service.CATEGORY_SERVICE.getCategory(categoryId);
        request.setAttribute("category", category);

        List<Room> rooms = Constant.Service.ROOM_SERVICE.getAll();
        request.setAttribute("rooms", rooms);

        request.getRequestDispatcher(Constant.Path.ADMIN_EDIT_CATEGORY).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryName = request.getParameter("categoryName");
        if (categoryName != null && !categoryName.trim().isEmpty()) {
            category.setCategoryName(categoryName);
        }

        String categoryDescription = request.getParameter("categoryDescription");
        if (categoryDescription != null && !categoryDescription.trim().isEmpty()) {
            category.setCategoryDescription(categoryDescription);
        }

        category.setRoom(Constant.Service.ROOM_SERVICE.getRoom(request.getParameter("room")));
        Constant.Service.CATEGORY_SERVICE.edit(category);

        response.sendRedirect(request.getContextPath() + "/admin/category/edit?id=" + categoryId);
    }
}