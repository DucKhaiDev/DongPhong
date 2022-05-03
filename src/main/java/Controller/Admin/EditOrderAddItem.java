package Controller.Admin;

import Controller.Client.LoginController;
import Entity.CartItem;
import Entity.Product;
import Util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.ListIterator;

@WebServlet(name = "EditOrderAddItem", value = "/admin/order/edit/add-product")
public class EditOrderAddItem extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (LoginController.checkLoginAdmin(request, response)) {
            return;
        }

        String forwardTo = request.getParameter("forwardTo");

        HttpSession session = request.getSession();

        //Order info
        String ord_recipientName = request.getParameter("recipientName");
        session.setAttribute("ord_recipientName", ord_recipientName);
        String ord_recipientPhone = request.getParameter("recipientPhone");
        session.setAttribute("ord_recipientPhone", ord_recipientPhone);

        String productId = request.getParameter("id");
        Product product = Constant.Service.PRODUCT_SERVICE.getProduct(productId);
        int productQuantity = Integer.parseInt(request.getParameter("num-product"));
        if (productQuantity > product.getProductQuantity()) {
            session.setAttribute("invalid", "Số lượng sản phẩm " + productId + " trong kho: " + product.getProductQuantity() + ". " +
                    "Vui lòng nhập số lượng hợp lệ!");
            response.sendRedirect(forwardTo);
            return;
        }

        /*
            If the product is already in the cart, add the quantity of the new product, otherwise add the product to the cart
        */
        //Get item
        CartItem cartItem = checkExistItem(productId, EditOrder.ord_items);
        if (cartItem != null) {
            /*
                Update item
            */
            //Update cartItem quantity
            int newQuantity = cartItem.getQuantity() + productQuantity;
            if (newQuantity > product.getProductQuantity()) {
                newQuantity = product.getProductQuantity();
            }
            cartItem.setQuantity(newQuantity);

            //Update cartItem value
            BigDecimal productPrice = product.getProductPrice();
            BigDecimal value = productPrice.multiply(new BigDecimal(cartItem.getQuantity()));
            cartItem.setValue(value);

            updateCart(EditOrder.ord_items, cartItem);
        } else {
            //Add product to cart
            CartItem item = new CartItem();
            BigDecimal productPrice = product.getProductPrice();
            BigDecimal value = productPrice.multiply(new BigDecimal(productQuantity));

            item.setQuantity(productQuantity);
            item.setValue(value);
            item.setProduct(product);
            item.setCart(EditOrder.ord_items.get(0).getCart());

            EditOrder.ord_items.add(item);
        }

        response.sendRedirect(forwardTo);
    }

    private CartItem checkExistItem(String productId, List<CartItem> ord_items) {
        for (CartItem item : ord_items) {
            if (item.getProduct().getProductId().equals(productId)) {
                return item;
            }
        }

        return null;
    }

    private void updateCart(List<CartItem> ord_items, CartItem cartItem) {
        ListIterator<CartItem> litr = ord_items.listIterator();
        while (litr.hasNext()) {
            CartItem item = litr.next();
            if (item.getCartItemId() == cartItem.getCartItemId()) {
                item.setCart(cartItem.getCart());
                item.setQuantity(cartItem.getQuantity());
                item.setValue(cartItem.getValue());
                item.setProduct(cartItem.getProduct());
                litr.set(item);
                return;
            }
        }
    }
}