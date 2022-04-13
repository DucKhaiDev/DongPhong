package Controller.Admin;

import Entity.Cart;
import Entity.Order;
import Entity.Payment;
import Entity.User;
import Services.deploy.OrderService;
import Services.deploy.PaymentService;
import Services.deploy.UserService;
import Util.Constant;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@WebServlet(name = "AddOrder", value = "/admin/order/add")
public class AddOrder extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Constant.Path.ADMIN_ADD_ORDER).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order order = new Order();
        order.setOrderSubTotal(new BigDecimal(request.getParameter("subTotal")));
        order.setOrderDiscount(new BigDecimal(request.getParameter("discount")));
        order.setOrderShipping(new BigDecimal(request.getParameter("shipping")));
        order.setOrderTax(new BigDecimal(request.getParameter("vat")));
        order.setOrderTotal(new BigDecimal(request.getParameter("total")));
        order.setUser(new UserService().getUser(request.getParameter("orderAccount")));
        order.setRecipientName(request.getParameter("fullName"));
        String recAddress = request.getParameter("recaddress") + ", "
                + request.getParameter("selectedWard") + ", "
                + request.getParameter("selectedDistrict") + ", "
                + request.getParameter("selectedProvince") + ".";
        order.setRecipientAddress(recAddress);
        order.setRecipientPhone(request.getParameter("phone"));
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        HttpSession session = request.getSession();
        order.setOrderSumProduct(((List<?>) session.getAttribute("cartItems")).size());
        order.setCart((Cart) session.getAttribute("cart"));
        Payment payment = new PaymentService().getPayment(request.getParameter("paymentMethod"));
        order.setPayment(payment);
        order.setOrderStatus(payment.getPaymentMethod());

        new OrderService().insert(order);
        /*String cartId = ((Cart) session.getAttribute("cart")).getCartId();
        cartItemService.deleteAll(cartId);
        List<CartItem> cartItems = cartItemService.getItemByCart(cartId);
        session.setAttribute("cartItems", cartItems);*/

        response.sendRedirect(request.getContextPath() + "/admin/order/detail?id=" + (new OrderService().getNewestOrder()).getOrderId());
    }
}
