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

@WebServlet(name = "ProductListByRoom", value = "/products/room")
public class ProductListByRoom extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roomId = request.getParameter("id");

        request.setAttribute("room", Constant.Service.ROOM_SERVICE.getRoom(roomId));
        request.setAttribute("categories", Constant.Service.CATEGORY_SERVICE.getCategoryByRoom(roomId));

        List<Product> products = Constant.Service.PRODUCT_SERVICE.getProductByRoom(roomId);

        //Search products
        String keyword = request.getParameter("search");
        if (keyword != null && !keyword.trim().isEmpty()) {
            products = Constant.Service.PRODUCT_SERVICE.searchByNameInRoom(roomId, keyword);
        }

        //Product page tools
        ProductListByCategory.productPageTools(request, products);

        request.getRequestDispatcher(Constant.Path.PRODUCT_LIST_BY_ROOM).forward(request, response);
    }
}