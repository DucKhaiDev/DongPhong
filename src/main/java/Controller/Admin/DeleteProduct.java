package Controller.Admin;

import Controller.Client.LoginController;
import Util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DeleteProduct", value = "/admin/product/delete")
public class DeleteProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (LoginController.checkLoginAdmin(request, response)) {
            return;
        }

        String productId = request.getParameter("id");

        //Delete product images
        Constant.Service.PRO_IMAGE_SERVICE.delete(productId);

        //Delete product
        Constant.Service.PRODUCT_SERVICE.delete(productId);

        response.sendRedirect(request.getContextPath() + "/admin/product");
    }
}