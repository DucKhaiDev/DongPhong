package Controller.Client;

import Entity.User;
import Services.deploy.UserService;
import Util.Constant;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ChangePassword", value = "/change-password")
public class ChangePassword extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Constant.Path.CHANGE_PASSWORD).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String repeatPassword = request.getParameter("repeatPassword");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");
        String cpwMsg;

        if (oldPassword.isEmpty() || newPassword.isEmpty() || repeatPassword.isEmpty()) {
            cpwMsg = "Vui lòng nhập đầy đủ các mục!";
            request.setAttribute("cpwMsg", cpwMsg);
            request.getRequestDispatcher(Constant.Path.CHANGE_PASSWORD).forward(request, response);
            return;
        }

        if (oldPassword.equals(user.getPassword())) {
            if (newPassword.equals(repeatPassword)) {
                user.setPassword(newPassword);
                userService.edit(user);
                response.sendRedirect("./logout");
            } else  {
                String cpw_notmatch = "Không trùng khớp!";
                request.setAttribute("cpw_notmatch", cpw_notmatch);
                request.getRequestDispatcher(Constant.Path.CHANGE_PASSWORD).forward(request, response);
            }
        } else {
            String cpw_incorrect = "Mật khẩu cũ không chính xác!";
            request.setAttribute("cpw_incorrect", cpw_incorrect);
            request.getRequestDispatcher(Constant.Path.CHANGE_PASSWORD).forward(request, response);
        }
    }
}
