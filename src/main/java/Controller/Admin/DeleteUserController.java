package Controller.Admin;

import Services.deploy.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DeleteUserController", value = "/admin/user/delete")
public class DeleteUserController extends HttpServlet {
    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userService.delete(request.getParameter("id"));
        response.sendRedirect(request.getContextPath() + "/admin/user");
    }
}
