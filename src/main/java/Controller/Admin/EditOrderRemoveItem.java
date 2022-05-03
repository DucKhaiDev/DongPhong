package Controller.Admin;

import Controller.Client.LoginController;
import Entity.CartItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ListIterator;

@WebServlet(name = "EditOrderRemoveItem", value = "/admin/order/edit/remove-product")
public class EditOrderRemoveItem extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!LoginController.checkLoginAdmin(request, response)) {
            return;
        }

        int cartItemId = Integer.parseInt(request.getParameter("id"));
        ListIterator<CartItem> litr = EditOrder.ord_items.listIterator();
        while (litr.hasNext()) {
            if (litr.next().getCartItemId() == cartItemId) {
                litr.remove();
                break;
            }
        }
        response.sendRedirect(request.getContextPath() + "/admin/order/edit?id=" + request.getParameter("orderId"));
    }
}
