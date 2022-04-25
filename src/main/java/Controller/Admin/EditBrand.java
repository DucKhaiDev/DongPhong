package Controller.Admin;

import Entity.Brand;
import Util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "EditBrand", value = "/admin/brand/edit")
public class EditBrand extends HttpServlet {
    private Brand brand;
    private String brandId;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        brandId = request.getParameter("id");

        brand = Constant.Service.BRAND_SERVICE.getBrand(brandId);
        request.setAttribute("brand", brand);

        request.getRequestDispatcher(Constant.Path.ADMIN_EDIT_BRAND).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String brandName = request.getParameter("brandName");
        if (brandName != null && !brandName.trim().isEmpty()) {
            brand.setBrandName(brandName);
        }

        String brandDescription = request.getParameter("brandDescription");
        if (brandDescription != null && !brandDescription.trim().isEmpty()) {
            brand.setBrandDescription(brandDescription);
        }

        Constant.Service.BRAND_SERVICE.edit(brand);

        response.sendRedirect(request.getContextPath() + "/admin/brand/edit?id=" + brandId);
    }
}