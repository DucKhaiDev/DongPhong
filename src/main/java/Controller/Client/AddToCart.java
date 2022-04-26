package Controller.Client;

import Entity.Cart;
import Entity.CartItem;
import Entity.Product;
import Entity.User;
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

@WebServlet(name = "AddToCart", value = "/cart/add")
public class AddToCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forwardTo = request.getParameter("forwardTo");

        //Check login
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");
        if (user == null) {
            session.setAttribute("forwardTo", forwardTo);
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String productId = request.getParameter("id");
        Product product = Constant.Service.PRODUCT_SERVICE.getProduct(productId);
        int productQuantity = Integer.parseInt(request.getParameter("num-product"));
        Cart cart = (Cart) session.getAttribute("cart");
        String cartId = cart.getCartId();

        /*
            If the product is already in the cart, add the quantity of the new product, otherwise add the product to the cart
        */
        if (Constant.Service.CART_ITEM_SERVICE.checkExistItem(productId, cartId)) {
            //Get item
            CartItem cartItem = Constant.Service.CART_ITEM_SERVICE.getCartItem(productId, cartId);

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

            Constant.Service.CART_ITEM_SERVICE.edit(cartItem);
        } else {
            //Add product to cart
            CartItem item = new CartItem();
            BigDecimal productPrice = product.getProductPrice();
            BigDecimal value = productPrice.multiply(new BigDecimal(productQuantity));

            item.setQuantity(productQuantity);
            item.setValue(value);
            item.setProduct(product);
            item.setCart(cart);

            Constant.Service.CART_ITEM_SERVICE.insert(item);
        }

        //Update cart items
        List<CartItem> cartItems = Constant.Service.CART_ITEM_SERVICE.getItemByCart(cartId);
        session.setAttribute("cartItems", cartItems);

        //Order info
        String ord_username = request.getParameter("username");
        session.setAttribute("ord_username", ord_username);
        String ord_recipientName = request.getParameter("recipientName");
        session.setAttribute("ord_recipientName", ord_recipientName);
        String ord_recipientPhone = request.getParameter("recipientPhone");
        session.setAttribute("ord_recipientPhone", ord_recipientPhone);

        response.sendRedirect(forwardTo);
    }
}