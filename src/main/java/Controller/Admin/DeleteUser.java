package Controller.Admin;

import Services.deploy.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DeleteUser", value = "/admin/user/delete")
public class DeleteUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        new UserService().delete(request.getParameter("id"));
        response.sendRedirect(request.getContextPath() + "/admin/user");
    }
}
