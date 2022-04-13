package Controller.Client;

import Util.Constant;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Enumeration;
import java.util.TreeSet;

@WebServlet(name = "LogoutController", value = "/logout")
public class LogoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        //Remove all attribute
        TreeSet<String> attributes = new TreeSet<>();
        Enumeration<String> enumeration = session.getAttributeNames();
        while (enumeration.hasMoreElements()) {
            attributes.add(enumeration.nextElement());
        }
        for (String attribute : attributes) {
            session.removeAttribute(attribute);
        }

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(Constant.COOKIE_USERNAME) || cookie.getName().equals(Constant.COOKIE_PASSWORD)) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }

        response.sendRedirect("./login");
    }
}
