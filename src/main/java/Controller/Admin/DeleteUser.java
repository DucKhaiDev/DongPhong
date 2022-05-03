package Controller.Admin;

import Controller.Client.LoginController;
import Entity.*;
import Util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "DeleteUser", value = "/admin/user/delete")
public class DeleteUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!LoginController.checkLoginAdmin(request, response)) {
            return;
        }

        User user = Constant.Service.USER_SERVICE.getUser(request.getParameter("id"));

        //Delete wishList
        String wishListId = Constant.Service.WISH_LIST_SERVICE.getWishListByUser(user.getUserId()).getWishListId();
        Constant.Service.WL_ITEM_SERVICE.deleteAll(wishListId);
        Constant.Service.WISH_LIST_SERVICE.delete(wishListId);

        //Delete order
        List<Order> orders = Constant.Service.ORDER_SERVICE.getAll(user);
        for (Order order : orders) {
            Constant.Service.ORDER_SERVICE.delete(order.getOrderId());
        }

        //Delete cart
        List<Cart> carts = Constant.Service.CART_SERVICE.getCartByUser(user.getUserId());
        for (Cart cart : carts) {
            Constant.Service.CART_ITEM_SERVICE.deleteAll(cart.getCartId());
            Constant.Service.CART_SERVICE.delete(cart.getCartId());
        }

        //Delete review
        Constant.Service.REVIEW_SERVICE.deleteAll(user.getUserId());

        Constant.Service.USER_SERVICE.delete(request.getParameter("id"));
        response.sendRedirect(request.getContextPath() + "/admin/user");
    }
}