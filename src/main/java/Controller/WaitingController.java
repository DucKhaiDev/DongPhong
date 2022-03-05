package Controller;

import Entity.User;
import Services.deploy.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "WaitingController", value = "/waiting")
public class WaitingController extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        //Sử dụng tài khoản đã lưu trong cookie
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        if (username != null && password != null) {
            User user = userService.login(username, password);
            session.setAttribute("account", user);
        }

        if (session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");

            //Kiểm tra customer đã tồn tại hay chưa
            String CUS_ID = "CUS_" + user.getUSERNAME();


            request.setAttribute("username", user.getUSERNAME());
            if (user.getROLE()) {
                response.sendRedirect(request.getContextPath() + "/welcome");
            } else {
                response.sendRedirect(request.getContextPath() + "/admin");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}
