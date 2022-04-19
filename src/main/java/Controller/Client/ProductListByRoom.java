package Controller.Client;

import Entity.Product;
import Entity.Room;
import Services.deploy.ProductService;
import Services.deploy.RoomService;
import Util.Constant;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductListByRoom", value = "/products/room")
public class ProductListByRoom extends HttpServlet {
    private final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roomId = request.getParameter("id");
        Room room = new RoomService().getRoom(roomId);
        request.setAttribute("room", room);
        List<Product> products = productService.getProductByRoom(roomId);

        //Search products
        String keyword = request.getParameter("search");
        if (keyword != null && !keyword.trim().isEmpty()) {
            products = productService.searchByNameInRoom(roomId, keyword);
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
        request.getRequestDispatcher(Constant.Path.PRODUCT_LIST_BY_ROOM).forward(request, response);
    }
}
