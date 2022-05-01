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

@WebServlet(name = "AddCategory", value = "/admin/category/add")
public class AddCategory extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (LoginController.checkLogin(request, response)) {
            return;
        }

        request.setAttribute("rooms", Constant.Service.ROOM_SERVICE.getAll());
        request.getRequestDispatcher(Constant.Path.ADMIN_ADD_CATEGORY).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryId = request.getParameter("categoryId");
        if (Constant.Service.CATEGORY_SERVICE.checkExistId(categoryId)) {
            request.setAttribute("existId", "Mã loại sản phẩm đã tồn tại!");
            request.getRequestDispatcher(Constant.Path.ADMIN_ADD_CATEGORY).forward(request, response);
            return;
        }

        String categoryName = request.getParameter("categoryName");
        Room room = Constant.Service.ROOM_SERVICE.getRoom(request.getParameter("room"));
        String categoryDescription = request.getParameter("categoryDescription");

        Constant.Service.CATEGORY_SERVICE.insert(new Category(categoryId, categoryName, room, categoryDescription));

        request.setAttribute("rooms", Constant.Service.ROOM_SERVICE.getAll());
        request.setAttribute("addSuccess", "Thêm loại sản phẩm thành công!");
        request.getRequestDispatcher(Constant.Path.ADMIN_ADD_CATEGORY).forward(request, response);
    }
}