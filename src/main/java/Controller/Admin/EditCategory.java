package Controller.Admin;

import Entity.Category;
import Entity.Room;
import Services.deploy.CategoryService;
import Services.deploy.RoomService;
import Util.Constant;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "EditCategory", value = "/admin/category/edit")
public class EditCategory extends HttpServlet {
    private final CategoryService categoryService = new CategoryService();
    private final RoomService roomService = new RoomService();
    private Category category;
    private String categoryId;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        categoryId = request.getParameter("id");
        category = categoryService.getCategory(categoryId);
        request.setAttribute("category", category);
        List<Room> rooms = roomService.getAll();
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

        category.setRoom(roomService.getRoom(request.getParameter("room")));

        categoryService.edit(category);
        response.sendRedirect(request.getContextPath() + "/admin/category/edit?id=" + categoryId);
    }
}
