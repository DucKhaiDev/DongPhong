package Controller.Client;

import Entity.*;
import Services.deploy.CartItemService;
import Services.deploy.CartService;
import Services.deploy.OrderService;
import Services.deploy.PaymentService;
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

@WebServlet(name = "Checkout", value = "/checkout")
public class Checkout extends HttpServlet {
    private final CartItemService cartItemService = new CartItemService();
    private Order order;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String subTotal = request.getParameter("subTotal");
        String discount = request.getParameter("discount");
        String shipping = request.getParameter("shipping");
        String vat = request.getParameter("vat");
        String total = request.getParameter("total");

        order = new Order();
        order.setOrderShipping(new BigDecimal(shipping == null ? "0" : shipping));
        order.setOrderTax(new BigDecimal(vat == null ? "0" : vat));
        order.setOrderSubTotal(new BigDecimal(subTotal == null ? "0" : subTotal));
        order.setOrderDiscount(new BigDecimal(discount == null ? "0" : discount));
        order.setOrderTotal(new BigDecimal(total == null ? "0" : total));

        List<Payment> payments = new PaymentService().getAll();
        request.setAttribute("payments", payments);

        request.getRequestDispatcher(Constant.Path.CHECKOUT).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String recName = request.getParameter("fullName");
        String recPhone = request.getParameter("phone");
        String recAddress = request.getParameter("recaddress") + ", "
                + request.getParameter("selectedWard") + ", "
                + request.getParameter("selectedDistrict") + ", "
                + request.getParameter("selectedProvince") + ".";
        order.setUser((User) session.getAttribute("account"));
        order.setRecipientName(recName);
        order.setRecipientAddress(recAddress);
        order.setRecipientPhone(recPhone);
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        order.setOrderSumProduct(((List<?>) session.getAttribute("cartItems")).size());
        order.setCart((Cart) session.getAttribute("cart"));
        Payment payment = new PaymentService().getPayment(request.getParameter("paymentMethod"));
        order.setPayment(payment);

        int paymentMethod = Integer.parseInt(request.getParameter("paymentMethod"));
        if (paymentMethod == 1) {
            return;
        } else if (paymentMethod == 2) {
            return;
        } else if (paymentMethod == 3) {
            return;
        } else if (paymentMethod == 4) {
            String message = "Cảm ơn bạn đã đặt hàng. Đơn hàng của bạn đã được ghi nhận. Vui lòng thanh toán khi nhận được hàng.";
            session.setAttribute("message", message);
        }

        new OrderService().insert(order);
        session.setAttribute("order_rv", new OrderService().getNewestOrder());
        session.setAttribute("cartItems_rv", cartItemService.getItemByCart(order.getCart().getCartId()));

        //Create new cart
        String CurrCartId = ((Cart) session.getAttribute("cart")).getCartId();
        int curr = Integer.parseInt(CurrCartId.split("-")[1]);
        User user = (User) session.getAttribute("account");
        String newCartId = user.getUsername() + "-" + (curr + 1);
        Cart cart = new Cart(newCartId, user);
        new CartService().insert(cart);
        List<CartItem> cartItems = cartItemService.getItemByCart(cart.getCartId());
        session.setAttribute("cartItems", cartItems);

        String[] attributes = {"recaddress", "selectedWard", "selectedDistrict", "selectedProvince", "shippingCost", "voucher"};
        for (String attribute : attributes) {
            session.removeAttribute(attribute);
        }

        response.sendRedirect(request.getContextPath() + "/checkout");
    }
}
