package Controller.Admin;

import Controller.WaitingController;
import Entity.*;
import Util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "EditOrder", value = "/admin/order/edit")
public class EditOrder extends HttpServlet {
    public static List<CartItem> ord_items = new ArrayList<>();
    private Order order;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("id"));

        order = Constant.Service.ORDER_SERVICE.getOrder(orderId);
        request.setAttribute("order", order);

        Cart cart = order.getCart();
        if (ord_items.isEmpty()) {
            ord_items.addAll(Constant.Service.CART_ITEM_SERVICE.getItemByCart(cart.getCartId()));
        }
        request.setAttribute("ord_items", ord_items);

        List<Voucher> availableVoucher = Constant.Service.VOUCHER_SERVICE.getAvailableVoucher(order.getCart().getCartId());
        availableVoucher.add(Constant.Service.VOUCHER_SERVICE.getVoucher("CHAOMUNG"));
        request.setAttribute("availableVoucher", availableVoucher);

        request.setAttribute("recipientAddress", order.getRecipientAddress());

        request.getRequestDispatcher(Constant.Path.ADMIN_EDIT_ORDER).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        order.setOrderSubTotal(new BigDecimal(request.getParameter("subTotal")));
        order.setOrderDiscount(new BigDecimal(request.getParameter("discount")));
        order.setOrderShipping(new BigDecimal(request.getParameter("shipping")));
        order.setOrderTax(new BigDecimal(request.getParameter("vat")));
        order.setOrderTotal(new BigDecimal(request.getParameter("total")));
        order.setRecipientName(request.getParameter("fullName"));
        order.setRecipientAddress(request.getParameter("recipientAddress"));
        order.setRecipientPhone(request.getParameter("phone"));
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        byte status = Byte.parseByte(request.getParameter("status"));
        order.setOrderStatus(status);
        if (status == 3) {
            String recipientDate = request.getParameter("recipientDate");
            if (recipientDate != null && !recipientDate.trim().isEmpty()) {
                try {
                    order.setRecipientDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(recipientDate).getTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        Payment payment = Constant.Service.PAYMENT_SERVICE.getPayment(request.getParameter("paymentMethod"));
        order.setPayment(payment);

        Constant.Service.ORDER_SERVICE.edit(order);

        //Update CartItem
        Constant.Service.CART_ITEM_SERVICE.deleteAll(order.getCart().getCartId());
        for (CartItem item : ord_items) {
            Constant.Service.CART_ITEM_SERVICE.insert(item);
        }

        //Remove all attributes
        WaitingController.removeAllAttr(request.getSession());

        response.sendRedirect(request.getContextPath() + "/admin/order/detail?id=" + order.getOrderId());
    }
}