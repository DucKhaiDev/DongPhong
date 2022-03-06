package Controller.Admin;

import Entity.Brand;
import Services.deploy.BrandService;
import Util.Constant;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "EditBrandController", value = "/admin/brand/edit")
public class EditBrandController extends HttpServlet {
    private final BrandService brandService = new BrandService();
    private Brand brand;
    private String brandId;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        brandId = request.getParameter("id");
        brand = brandService.getBrand(brandId);
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

        brandService.edit(brand);
        response.sendRedirect(request.getContextPath() + "/admin/brand/edit?id=" + brandId);
    }
}
