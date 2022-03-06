package Controller.Admin;

import Services.deploy.ProImageService;
import Services.deploy.ProductService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DeleteProductController", value = "/admin/product/delete")
public class DeleteProductController extends HttpServlet {
    private final ProductService productService = new ProductService();
    private final ProImageService imageService = new ProImageService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("id");
        //Xóa ảnh sản phẩm
        imageService.delete(productId);
        //Xóa sản phẩm
        productService.delete(productId);
        response.sendRedirect(request.getContextPath() + "/admin/product");
    }
}
