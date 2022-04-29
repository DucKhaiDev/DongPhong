package Controller.Client;

import Entity.User;
import Util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "MyOrder", value = "/member/my-order")
public class MyOrder extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("myOrders", Constant.Service.ORDER_SERVICE.getAll((User) request.getSession().getAttribute("account")));
        request.getRequestDispatcher(Constant.Path.MY_ORDER).forward(request, response);
    }
}