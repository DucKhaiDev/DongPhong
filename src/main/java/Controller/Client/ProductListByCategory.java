package Controller.Client;

import Entity.Category;
import Entity.Product;
import Util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductListByCategory", value = "/products/category")
public class ProductListByCategory extends HttpServlet {
    public static void productPageTools(HttpServletRequest request, List<Product> products) throws ServletException, IOException {
        String sortBy = request.getParameter("sortBy");
        if (sortBy != null) {
            switch (sortBy) {
                case "priceAsc":
                    Constant.Service.PRODUCT_SERVICE.sortByPriceAsc(products);
                    break;
                case "priceDesc":
                    Constant.Service.PRODUCT_SERVICE.sortByPriceDesc(products);
                    break;
                case "rateAsc":
                    Constant.Service.PRODUCT_SERVICE.sortByRateAsc(products);
                    break;
                case "rateDesc":
                    Constant.Service.PRODUCT_SERVICE.sortByRateDesc(products);
                    break;
                case "saleAsc":
                    Constant.Service.PRODUCT_SERVICE.sortBySaleAsc(products);
                    break;
                case "saleDesc":
                    Constant.Service.PRODUCT_SERVICE.sortBySaleDesc(products);
                    break;
            }

        }

        //Filter brand
        String brands = request.getParameter("brand");
        if (brands != null) {
            products = Constant.Service.PRODUCT_SERVICE.filterProductByBrand(products, brands);
        }

        //Filter category
        String categories = request.getParameter("category");
        if (categories != null) {
            products = Constant.Service.PRODUCT_SERVICE.filterProductByCategory(products, categories);
        }

        //Filter prices
        String price = request.getParameter("price");
        if (price != null) {
            products = Constant.Service.PRODUCT_SERVICE.filterProductByPrice(products, price);
        }

        //Filter star rating
        String stars = request.getParameter("rating");
        if (stars != null) {
            products = Constant.Service.PRODUCT_SERVICE.filterProductByStars(products, stars);
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

        request.setAttribute("products", products);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryId = request.getParameter("id");
        Category category = Constant.Service.CATEGORY_SERVICE.getCategory(categoryId);
        request.setAttribute("category", category);
        List<Product> products = Constant.Service.PRODUCT_SERVICE.getProductByCategory(categoryId);

        //Search products
        String keyword = request.getParameter("search");
        if (keyword != null && !keyword.trim().isEmpty()) {
            products = Constant.Service.PRODUCT_SERVICE.searchByNameInCategory(categoryId, keyword);
        }

        //Product page tools
        ProductListByCategory.productPageTools(request, products);

        request.getRequestDispatcher(Constant.Path.PRODUCT_LIST_BY_CATEGORY).forward(request, response);
    }
}