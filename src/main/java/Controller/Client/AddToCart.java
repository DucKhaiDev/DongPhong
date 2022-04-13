package Controller.Client;

import Entity.Cart;
import Entity.CartItem;
import Entity.Product;
import Entity.User;
import Services.deploy.CartItemService;
import Services.deploy.ProductService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet(name = "AddToCart", value = "/cart/add")
public class AddToCart extends HttpServlet {
    private final ProductService productService = new ProductService();
    private final CartItemService cartItemService = new CartItemService();

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
        Product product = productService.getProduct(productId);
        int productQuantity = Integer.parseInt(request.getParameter("num-product"));
        Cart cart = (Cart) session.getAttribute("cart");
        String cartId = cart.getCartId();

        /*
            If the product is already in the cart, add the quantity of the new product, otherwise add the product to the cart
        */
        if (cartItemService.checkExistItem(productId, cartId)) {
            //Get item
            CartItem cartItem = cartItemService.getCartItem(productId, cartId);

            /*
                Update item
            */
            //Update cartItem quantity
            cartItem.setQuantity(cartItem.getQuantity() + productQuantity);
            //Update cartItem value
            BigDecimal productPrice = product.getProductPrice();
            BigDecimal value = productPrice.multiply(new BigDecimal(cartItem.getQuantity()));
            cartItem.setValue(value);

            cartItemService.edit(cartItem);
        } else {
            //Add product to cart
            CartItem item = new CartItem();
            item.setQuantity(productQuantity);
            BigDecimal productPrice = product.getProductPrice();
            BigDecimal value = productPrice.multiply(new BigDecimal(productQuantity));
            item.setValue(value);
            item.setProduct(product);
            item.setCart(cart);

            cartItemService.insert(item);
        }

        //Update cart items
        List<CartItem> cartItems = cartItemService.getItemByCart(cartId);
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
