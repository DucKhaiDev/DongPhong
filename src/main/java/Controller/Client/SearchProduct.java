package Controller.Client;

import Entity.Product;
import Services.deploy.ProductService;
import Util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "SearchProduct", value = "/search")
public class SearchProduct extends HttpServlet {
    private final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword-search-header");
        request.setAttribute("keyword", keyword);
        List<Product> products = productService.searchByName(keyword);

        //Search products
        String key = request.getParameter("search");
        if (key != null && !key.trim().isEmpty()) {
            String finalKey = key.toUpperCase();
            products.removeIf(product -> !product.getProductName().toUpperCase().contains(finalKey));
        }

        //Sort products
        String sortBy = request.getParameter("sortBy");
        if (sortBy != null) {
            if (sortBy.equals("priceAsc")) {
                productService.sortByPriceAsc(products);
            }

            if (sortBy.equals("priceDesc")) {
                productService.sortByPriceDesc(products);
            }
        }

        //Pagination
        int totalNumber = products.size();
        request.setAttribute("totalNumber", totalNumber);
        int pageSize = 12;
        request.setAttribute("pageSize", pageSize);
        String page = request.getParameter("page");
        int pageNumber = 1;
        if (page != null) {
            pageNumber = Integer.parseInt(page);
        }
        int fromIndex = (pageNumber - 1) * pageSize;
        int toIndex = fromIndex + pageSize;
        products = products.subList(fromIndex, Math.min(toIndex, totalNumber));

        //Filter brand
        String brands = request.getParameter("brand");
        if (brands != null) {
            products = productService.filterProductByBrand(products, brands);
        }

        //Filter prices
        String price = request.getParameter("price");
        if (price != null) {
            products = productService.filterProductByPrice(products, price);
        }

        //Filter star rating
        String stars = request.getParameter("stars-rating");
        if (stars != null) {
            products = productService.filterProductByStars(products, stars);
        }

        request.setAttribute("products", products);
        request.getRequestDispatcher(Constant.Path.PRODUCT_SEARCH).forward(request, response);
    }
}
