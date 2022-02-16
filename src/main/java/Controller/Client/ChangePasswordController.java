package Controller.Client;

import Entity.User;
import Services.deploy.UserService;
import Util.Constant;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ChangePasswordController", value = "/change-password")
public class ChangePasswordController extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Constant.Path.CHANGE_PASSWORD).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldPassword = request.getParameter("old_password");
        String newPassword = request.getParameter("new_password");
        String repeatNewPassword = request.getParameter("repeat_new_password");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");
        String cpwMsg = "";

        if (oldPassword.isEmpty() || newPassword.isEmpty() || repeatNewPassword.isEmpty()) {
            cpwMsg = "Vui lòng nhập đầy đủ các mục!";
            request.setAttribute("cpwMsg", cpwMsg);
            request.getRequestDispatcher(Constant.Path.CHANGE_PASSWORD).forward(request, response);
            return;
        }

        if (oldPassword.equals(user.getPASSWORD())) {
            if (newPassword.equals(repeatNewPassword)) {
                user.setPASSWORD(newPassword);
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
