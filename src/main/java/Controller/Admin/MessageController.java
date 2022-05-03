package Controller.Admin;

import Controller.Client.LoginController;
import Util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "MessageController", value = "/admin/message")
public class MessageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (LoginController.checkLoginAdmin(request, response)) {
            return;
        }

        request.setAttribute("messages", Constant.Service.MESSAGE_SERVICE.getAll());
        request.getRequestDispatcher(Constant.Path.ADMIN_MESSAGE).forward(request, response);
    }
}
