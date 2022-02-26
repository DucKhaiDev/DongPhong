package Controller.Client;

import Entity.Category;
import Entity.Product;
import Services.deploy.CategoryService;
import Services.deploy.ProductService;
import Util.Constant;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductListByCategory", value = "/products/category")
public class ProductListByCategory extends HttpServlet {
    private ProductService productService = new ProductService();
    private CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String CAT_ID = request.getParameter("id");
        Category category = categoryService.getCategory(CAT_ID);
        request.setAttribute("category", category);
        List<Product> products = productService.getProductByCategory(CAT_ID);
        request.setAttribute("products", products);
        request.getRequestDispatcher(Constant.Path.PRODUCT_LIST_BY_CATEGORY).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
