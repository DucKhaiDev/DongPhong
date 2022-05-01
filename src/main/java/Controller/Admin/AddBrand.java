package Controller.Admin;

import Controller.Client.LoginController;
import Entity.Brand;
import Util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AddBrand", value = "/admin/brand/add")
public class AddBrand extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (LoginController.checkLogin(request, response)) {
            return;
        }

        request.getRequestDispatcher(Constant.Path.ADMIN_ADD_BRAND).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String brandId = request.getParameter("brandId");
        if (Constant.Service.BRAND_SERVICE.checkExistId(brandId)) {
            String existId = "Mã thương hiệu đã tồn tại!";
            request.setAttribute("existId", existId);
            request.getRequestDispatcher(Constant.Path.ADMIN_ADD_BRAND).forward(request, response);
            return;
        }

        String brandName = request.getParameter("brandName");
        String brandDes = request.getParameter("brandDescription");

        Constant.Service.BRAND_SERVICE.insert(new Brand(brandId, brandName, brandDes));

        request.setAttribute("addSuccess", "Thêm thương hiệu thành công!");
        request.getRequestDispatcher(Constant.Path.ADMIN_ADD_BRAND).forward(request, response);
    }
}