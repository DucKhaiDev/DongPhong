package Controller.Client;

import Entity.Product;
import Util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchProduct", value = "/search")
public class SearchProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword-search-header");
        request.setAttribute("keyword", keyword);
        List<Product> products = Constant.Service.PRODUCT_SERVICE.searchByName(keyword);

        request.setAttribute("rooms", Constant.Service.ROOM_SERVICE.getAll());
        request.setAttribute("categories", Constant.Service.CATEGORY_SERVICE.getAll());

        //Search products
        String key = request.getParameter("search");
        if (key != null && !key.trim().isEmpty()) {
            String finalKey = key.toUpperCase();
            products.removeIf(product -> !product.getProductName().toUpperCase().contains(finalKey));
        }

        //Product page tools
        ProductListByCategory.productPageTools(request, products);

        request.getRequestDispatcher(Constant.Path.PRODUCT_SEARCH).forward(request, response);
    }
}