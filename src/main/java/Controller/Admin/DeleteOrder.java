package Controller.Admin;

import Services.deploy.OrderService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DeleteOrder", value = "/admin/order/delete")
public class DeleteOrder extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("id"));
        new OrderService().delete(orderId);
        response.sendRedirect(request.getContextPath() + "/admin/order");
    }
}
