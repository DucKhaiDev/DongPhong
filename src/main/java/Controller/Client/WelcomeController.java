package Controller.Client;

import Tools.ReleaseMemory;
import Util.Constant;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "WelcomeController", value = "/welcome")
public class WelcomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReleaseMemory.deleteUnusedImg();
        request.getRequestDispatcher(Constant.Path.HOME).forward(request, response);
    }
}
