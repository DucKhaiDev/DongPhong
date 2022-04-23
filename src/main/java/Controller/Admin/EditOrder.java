package Controller.Admin;

import Entity.Cart;
import Entity.CartItem;
import Entity.Order;
import Entity.Payment;
import Services.deploy.CartItemService;
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

@WebServlet(name = "EditOrder", value = "/admin/order/edit")
public class EditOrder extends HttpServlet {
    private final OrderService orderService = new OrderService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("id"));
        Order order = orderService.getOrder(orderId);
        request.setAttribute("order", order);
        Cart cart = order.getCart();
        List<CartItem> cartItems = new CartItemService().getItemByCart(cart.getCartId());
        request.setAttribute("cartItems", cartItems);
        HttpSession session = request.getSession();
        session.setAttribute("shippingCost", order.getOrderShipping());
        session.setAttribute("recipientAddress", order.getRecipientAddress());
        request.getRequestDispatcher(Constant.Path.ADMIN_EDIT_ORDER).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order order = new Order();
        order.setOrderId(Integer.parseInt(request.getParameter("orderId")));
        order.setOrderSubTotal(new BigDecimal(request.getParameter("subTotal")));
        order.setOrderDiscount(new BigDecimal(request.getParameter("discount")));
        order.setOrderShipping(new BigDecimal(request.getParameter("shipping")));
        order.setOrderTax(new BigDecimal(request.getParameter("vat")));
        order.setOrderTotal(new BigDecimal(request.getParameter("total")));
        order.setUser(new UserService().getUser(request.getParameter("orderAccount")));
        order.setRecipientName(request.getParameter("fullName"));
        order.setRecipientAddress(request.getParameter("recipientAddress"));
        order.setRecipientPhone(request.getParameter("phone"));
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        HttpSession session = request.getSession();
        order.setOrderSumProduct(((List<?>) session.getAttribute("cartItems")).size());
        order.setCart((Cart) session.getAttribute("cart"));
        Payment payment = new PaymentService().getPayment(request.getParameter("paymentMethod"));
        order.setPayment(payment);
        order.setOrderStatus(false);

        new OrderService().edit(order);
        /*String cartId = ((Cart) session.getAttribute("cart")).getCartId();
        cartItemService.deleteAll(cartId);
        List<CartItem> cartItems = cartItemService.getItemByCart(cartId);
        session.setAttribute("cartItems", cartItems);*/
        session.removeAttribute("orderAccount");
        session.removeAttribute("ord_recipientName");
        session.removeAttribute("ord_recipientPhone");
        session.removeAttribute("selectedProvince");
        session.removeAttribute("selectedDistrict");
        session.removeAttribute("selectedWard");
        session.removeAttribute("recaddress");
        session.removeAttribute("shippingCost");

        response.sendRedirect(request.getContextPath() + "/admin/order/detail?id=" + (new OrderService().getNewestOrder()).getOrderId());
    }
}
