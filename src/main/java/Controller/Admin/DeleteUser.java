package Controller.Admin;

import Services.deploy.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DeleteUser", value = "/admin/user/delete")
public class DeleteUser extends HttpServlet {
    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userService.delete(request.getParameter("id"));
        response.sendRedirect(request.getContextPath() + "/admin/user");
    }
}