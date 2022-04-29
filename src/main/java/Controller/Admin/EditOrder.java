package Controller.Admin;

import Entity.Cart;
import Entity.CartItem;
import Entity.Order;
import Entity.Payment;
import Util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@WebServlet(name = "EditOrder", value = "/admin/order/edit")
public class EditOrder extends HttpServlet {
    private Order order;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("id"));

        order = Constant.Service.ORDER_SERVICE.getOrder(orderId);
        request.setAttribute("order", order);

        Cart cart = order.getCart();
        List<CartItem> cartItems = Constant.Service.CART_ITEM_SERVICE.getItemByCart(cart.getCartId());
        request.setAttribute("cartItems", cartItems);

        HttpSession session = request.getSession();
        session.setAttribute("shippingCost", order.getOrderShipping());
        session.setAttribute("recipientAddress", order.getRecipientAddress());

        request.getRequestDispatcher(Constant.Path.ADMIN_EDIT_ORDER).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        order.setOrderSubTotal(new BigDecimal(request.getParameter("subTotal")));
        order.setOrderDiscount(new BigDecimal(request.getParameter("discount")));
        order.setOrderShipping(new BigDecimal(request.getParameter("shipping")));
        order.setOrderTax(new BigDecimal(request.getParameter("vat")));
        order.setOrderTotal(new BigDecimal(request.getParameter("total")));
        order.setUser(Constant.Service.USER_SERVICE.getUser(request.getParameter("orderAccount")));
        order.setRecipientName(request.getParameter("fullName"));
        order.setRecipientAddress(request.getParameter("recipientAddress"));
        order.setRecipientPhone(request.getParameter("phone"));
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));

        Payment payment = Constant.Service.PAYMENT_SERVICE.getPayment(request.getParameter("paymentMethod"));
        order.setPayment(payment);
        order.setOrderStatus(Byte.parseByte(request.getParameter("orderStatus")));

        Constant.Service.ORDER_SERVICE.edit(order);

        String[] attributes = {
                "orderAccount",
                "ord_recipientName",
                "ord_recipientPhone",
                "selectedProvince",
                "selectedDistrict",
                "selectedWard",
                "recaddress",
                "shippingCost"
        };

        HttpSession session = request.getSession();
        for (String attribute : attributes) {
            session.removeAttribute(attribute);
        }

        response.sendRedirect(request.getContextPath() + "/admin/order/detail?id=" + (Constant.Service.ORDER_SERVICE.getNewestOrder()).getOrderId());
    }
}