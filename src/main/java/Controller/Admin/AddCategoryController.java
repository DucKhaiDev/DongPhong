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

@WebServlet(name = "AddCategoryController", value = "/admin/category/add")
public class AddCategoryController extends HttpServlet {
    private final CategoryService categoryService = new CategoryService();
    private final RoomService roomService = new RoomService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Room> rooms = roomService.getAll();
        request.setAttribute("rooms", rooms);
        request.getRequestDispatcher(Constant.Path.ADMIN_ADD_CATEGORY).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryId = request.getParameter("categoryId");
        if (categoryService.checkExistId(categoryId)) {
            String existId = "Mã loại sản phẩm đã tồn tại!";
            request.setAttribute("existId", existId);
            request.getRequestDispatcher(Constant.Path.ADMIN_ADD_CATEGORY).forward(request, response);
            return;
        }

        String categoryName = request.getParameter("categoryName");
        Room room = roomService.getRoom(request.getParameter("room"));
        String categoryDescription = request.getParameter("categoryDescription");
        Category category = new Category(categoryId, categoryName, room, categoryDescription);
        categoryService.insert(category);
        response.sendRedirect(request.getContextPath() + "/admin/category");
    }
}
