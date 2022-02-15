package Controller.Admin;

import Entity.User;
import ServicesImpl.UserServiceImpl;
import Util.Constant;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserController", urlPatterns = "/admin/user")
public class UserController extends HttpServlet {
    UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> userList = userService.getAll();
        request.setAttribute("userList", userList);
        request.getRequestDispatcher(Constant.Path.ADMIN_USER).forward(request, response);
    }
}
