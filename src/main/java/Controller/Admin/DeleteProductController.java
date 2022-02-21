package Controller.Admin;

import Services.deploy.ProImageService;
import Services.deploy.ProductService;
import Util.Constant;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DeleteProductController", value = "/admin/product/delete")
public class DeleteProductController extends HttpServlet {
    private ProductService productService = new ProductService();
    private ProImageService imageService = new ProImageService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String PRO_ID = request.getParameter("id");
        //Xóa ảnh sản phẩm
        imageService.delete(PRO_ID);
        //Xóa sản phẩm
        productService.delete(PRO_ID);
        response.sendRedirect(request.getContextPath() + "/admin/product");
    }
}
