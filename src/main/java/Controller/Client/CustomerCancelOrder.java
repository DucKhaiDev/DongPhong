package Controller.Client;

import Entity.Order;
import Entity.User;
import Util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@WebServlet(name = "CustomerCancelOrder", value = "/member/my-order/cancel")
public class CustomerCancelOrder extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!LoginController.checkLogin(request, response)) {
            return;
        }

        Order order = Constant.Service.ORDER_SERVICE.getOrder(Integer.parseInt(request.getParameter("id")));

        //Check account
        HttpSession session = request.getSession();
        User sysUser = (User) session.getAttribute("account");
        if (!sysUser.getUsername().equals(order.getUser().getUsername())) {
            response.sendRedirect(request.getContextPath() + "/logout");
            return;
        }

        Timestamp orderDate = order.getOrderDate();
        if (orderDate.compareTo(new Timestamp(Date.from(LocalDate.now().minusDays(2).atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime())) < 0) {
            session.setAttribute("overtime", "Đã quá thời hạn hủy!");
            response.sendRedirect(request.getContextPath() + "/member/my-order");
            return;
        }

        order.setOrderStatus((byte) 4);
        Constant.Service.ORDER_SERVICE.edit(order);
        response.sendRedirect(request.getContextPath() + "/member/my-order");
    }
}