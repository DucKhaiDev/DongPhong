package Controller.Admin;

import Entity.User;
import Services.deploy.UserService;
import Util.Constant;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserController", value = "/admin/user")
public class UserController extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userService.getAll();
        request.setAttribute("users", users);
        int countAdmin = userService.countAdmin();
        request.setAttribute("countAdmin", countAdmin);
        request.getRequestDispatcher(Constant.Path.ADMIN_USER).forward(request, response);
    }
}
