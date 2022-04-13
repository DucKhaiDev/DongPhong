package Controller.Client;

import Entity.*;
import Services.deploy.CartItemService;
import Services.deploy.OrderService;
import Services.deploy.PaymentService;
import Util.Constant;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@WebServlet(name = "Checkout", value = "/checkout")
public class Checkout extends HttpServlet {
    private Order order;
    private final CartItemService cartItemService = new CartItemService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String subTotal = request.getParameter("subTotal");
        String discount = request.getParameter("discount");
        String shipping = request.getParameter("shipping");
        String vat = request.getParameter("vat");
        String total = request.getParameter("total");

        order = new Order();
        order.setOrderShipping(new BigDecimal(shipping));
        order.setOrderTax(new BigDecimal(vat));
        order.setOrderSubTotal(new BigDecimal(subTotal));
        order.setOrderDiscount(new BigDecimal(discount));
        order.setOrderTotal(new BigDecimal(total));

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
            order.setOrderStatus(payment.getPaymentMethod());
            String message = "Cảm ơn bạn đã đặt hàng. Đơn hàng của bạn đã được ghi nhận. Vui lòng thanh toán khi nhận được hàng.";
            session.setAttribute("message", message);
        }

        new OrderService().insert(order);
        session.setAttribute("order", new OrderService().getNewestOrder());
        session.setAttribute("cartItems", cartItemService.getItemByCart(order.getCart().getCartId()));
        /*String cartId = ((Cart) session.getAttribute("cart")).getCartId();
        cartItemService.deleteAll(cartId);
        List<CartItem> cartItems = cartItemService.getItemByCart(cartId);
        session.setAttribute("cartItems", cartItems);*/

        response.sendRedirect(request.getContextPath() + "/checkout");
    }
}
