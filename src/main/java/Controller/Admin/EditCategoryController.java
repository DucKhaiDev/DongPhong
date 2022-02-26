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

@WebServlet(name = "EditCategoryController", value = "/admin/category/edit")
public class EditCategoryController extends HttpServlet {
    private CategoryService categoryService = new CategoryService();
    private RoomService roomService = new RoomService();
    private Category category;
    private String CAT_ID;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CAT_ID = request.getParameter("id");
        category = categoryService.getCategory(CAT_ID);
        request.setAttribute("category", category);
        List<Room> rooms = roomService.getAll();
        request.setAttribute("rooms", rooms);
        request.getRequestDispatcher(Constant.Path.ADMIN_EDIT_CATEGORY).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String CAT_NAME = request.getParameter("cat_name");
        if (CAT_NAME != null && !CAT_NAME.trim().isEmpty()) {
            category.setCAT_NAME(CAT_NAME);
        }

        String CAT_DES = request.getParameter("cat_des");
        if (CAT_DES != null && !CAT_DES.trim().isEmpty()) {
            category.setCAT_DES(CAT_DES);
        }

        category.setROOM(roomService.getRoom(request.getParameter("room")));

        categoryService.edit(category);
        response.sendRedirect(request.getContextPath() + "/admin/category/edit?id=" + CAT_ID);
    }
}
