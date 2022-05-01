package Controller.Client;

import Entity.User;
import Util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {
    public static boolean checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getSession().getAttribute("account") == null) {
            response.sendRedirect(request.getContextPath() + "/logout");
            return true;
        }

        return false;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            response.sendRedirect(request.getContextPath() + "/waiting");
            return;
        }

        //Check cookie
        Cookie[] cookies = request.getCookies();
        String username = "", password = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                    session = request.getSession(true);
                    session.setAttribute("username", cookie.getValue());
                } else if (cookie.getName().equals("password")) {
                    password = cookie.getValue();
                    session = request.getSession(true);
                    session.setAttribute("password", cookie.getValue());
                }

                if (!username.isEmpty() && !password.isEmpty()) {
                    response.sendRedirect(request.getContextPath() + "/waiting");
                    return;
                }
            }
        }

        request.getRequestDispatcher(Constant.Path.LOGIN).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String rememberMe = request.getParameter("rememberMe");
        boolean isRememberMe = rememberMe != null;

        String loginMsg;

        User user = Constant.Service.USER_SERVICE.login(username, password);

        if (user != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("account", user);

            if (isRememberMe) {
                saveRememberMe(response, username, password);
            }

            response.sendRedirect(request.getContextPath() + "/waiting");
        } else {
            loginMsg = "Email/Tên đăng nhập hoặc mật khẩu không chính xác!";
            request.setAttribute("loginMsg", loginMsg);
            request.getRequestDispatcher(Constant.Path.LOGIN).forward(request, response);
        }
    }

    private void saveRememberMe(HttpServletResponse response, String username, String password) {
        //Remember login information for: 3 days
        Cookie ckUsername = new Cookie(Constant.COOKIE_USERNAME, username);
        ckUsername.setMaxAge(3 * 24 * 60 * 60);
        response.addCookie(ckUsername);

        Cookie ckPassword = new Cookie(Constant.COOKIE_PASSWORD, password);
        ckPassword.setMaxAge(3 * 24 * 60 * 60);
        response.addCookie(ckPassword);
    }
}