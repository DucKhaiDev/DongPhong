package Controller;

import Entity.User;
import ServicesImpl.UserServiceImpl;
import Util.Constant;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            response.sendRedirect(request.getContextPath() + "/waiting");
            return;
        }

        //Check cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie: cookies) {
                if (cookie.getName().equals("username_or_email")) {
                    session = request.getSession(true);
                    session.setAttribute("username_or_email", cookie.getValue());
                    response.sendRedirect(request.getContextPath() + "/waiting");
                    return;
                }
            }
        }

        request.getRequestDispatcher(Constant.Path.LOGIN).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username_or_email = request.getParameter("username_or_email").trim();
        String password = request.getParameter("password").trim();
        String rememberMe = request.getParameter("rememberMe");
        boolean isRememberMe = rememberMe != null;

        String loginMsg = "";

        if (username_or_email.isEmpty() || password.isEmpty()) {
            loginMsg = "Email/Tên đăng nhập và mật khẩu không được để trống!";
            request.setAttribute("loginMsg", loginMsg);
            RequestDispatcher rd = request.getRequestDispatcher(Constant.Path.LOGIN);
            rd.include(request, response);
            return;
        }

        UserServiceImpl service = new UserServiceImpl();

        User user = service.login(username_or_email, password);

        if (user != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("account", user);

            if (isRememberMe) {
                saveRememberMe(response, username_or_email, password);
            }

            response.sendRedirect(request.getContextPath() + "/waiting");
        } else {
            loginMsg = "Email/Tên đăng nhập hoặc mật khẩu không chính xác!";
            request.setAttribute("loginMsg", loginMsg);
            request.getRequestDispatcher(Constant.Path.LOGIN).forward(request, response);
        }
    }

    private void saveRememberMe(HttpServletResponse response, String username_or_email, String password) {
        Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER, username_or_email);
        cookie.setMaxAge(24*60*60);
        response.addCookie(cookie);
    }
}
