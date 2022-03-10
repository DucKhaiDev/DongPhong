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

        //Kiểm tra đăng nhập
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
            Nếu sản phẩm đã tồn tại trong giỏ hàng, cộng thêm số lượng sản phẩm mới
            Nếu chưa tồn tại, thêm sản phẩm vào giỏ hàng
        */
        if (cartItemService.checkExistItem(productId, cartId)) {
            //Get item
            CartItem cartItem = cartItemService.getCartItem(productId, cartId);

            /*
                Cập nhật item
            */
            //Cập nhật số lượng sản phẩm
            cartItem.setQuantity(cartItem.getQuantity() + productQuantity);
            //Cập nhật giá trị
            BigDecimal productPrice = new BigDecimal(product.getProductPrice());
            BigDecimal value = productPrice.multiply(new BigDecimal(cartItem.getQuantity()));
            cartItem.setValue(value.toString());

            cartItemService.edit(cartItem);
        } else {
            //Thêm sản phẩm vào giỏ hàng
            CartItem item = new CartItem();
            item.setQuantity(productQuantity);
            BigDecimal productPrice = new BigDecimal(product.getProductPrice());
            BigDecimal value = productPrice.multiply(new BigDecimal(productQuantity));
            item.setValue(value.toString());
            item.setProduct(product);
            item.setCart(cart);

            cartItemService.insert(item);
        }

        //Cập nhật giỏ hàng
        List<CartItem> cartItems = cartItemService.getItemByCart(cartId);
        session.setAttribute("cartItems", cartItems);

        response.sendRedirect(forwardTo);
    }
}
