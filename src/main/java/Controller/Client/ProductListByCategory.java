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
    private final ProductService productService = new ProductService();
    private final CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String CAT_ID = request.getParameter("id");
        Category category = categoryService.getCategory(CAT_ID);
        request.setAttribute("category", category);
        List<Product> products = productService.getProductByCategory(CAT_ID);

        //Tìm kiếm sản phẩm
        String keyword = request.getParameter("search");
        if (keyword != null && !keyword.trim().isEmpty()) {
            products = productService.searchByNameInCategory(CAT_ID, keyword);
        }

        //Sắp xếp sản phẩm
        String sortBy = request.getParameter("sortBy");
        if (sortBy != null) {
            if (sortBy.equals("priceAsc")) {
                productService.sortByPriceAsc(products);
            }

            if (sortBy.equals("priceDesc")) {
                productService.sortByPriceDesc(products);
            }
        }

        //Phân trang
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

        //Lọc thương hiệu
        String brands = request.getParameter("brand");
        if (brands != null) {
            products = productService.filterProductByBrand(products, brands);
        }

        //Lọc giá
        String price = request.getParameter("price");
        if (price != null) {
            products = productService.filterProductByPrice(products, price);
        }

        //Lọc số sao đánh giá
        String stars = request.getParameter("stars-rating");
        if (stars != null) {
            products = productService.filterProductByStars(products, stars);
        }

        request.setAttribute("products", products);
        request.getRequestDispatcher(Constant.Path.PRODUCT_LIST_BY_CATEGORY).forward(request, response);
    }
}