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
    private CategoryService categoryService = new CategoryService();
    private RoomService roomService = new RoomService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Room> rooms = roomService.getAll();
        request.setAttribute("rooms", rooms);
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
        Room room = roomService.getRoom(request.getParameter("room"));
        String CAT_DES = request.getParameter("cat_des");
        Category category = new Category(CAT_ID, CAT_NAME, room, CAT_DES);
        categoryService.insert(category);
        response.sendRedirect(request.getContextPath() + "/admin/category");
    }
}
