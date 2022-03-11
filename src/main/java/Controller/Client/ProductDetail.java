package Controller.Client;

import Entity.Product;
import Services.deploy.ProductService;
import Util.Constant;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductDetail", value = "/products/product-detail")
public class ProductDetail extends HttpServlet {
    private final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("id");
        Product product = productService.getProduct(productId);
        request.setAttribute("product", product);

        //Sản phẩm liên quan
        List<Product> relatedProducts = productService.getProductByCategory(product.getCategory().getCategoryId());
        request.setAttribute("relatedProducts", relatedProducts);

        request.getRequestDispatcher(Constant.Path.PRODUCT_DETAIL).forward(request, response);
    }
}
