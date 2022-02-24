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
    private BrandService brandService = new BrandService();
    private Brand brand;
    private String BRA_ID;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BRA_ID = request.getParameter("id");
        brand = brandService.getBrand(BRA_ID);
        request.setAttribute("brand", brand);
        request.getRequestDispatcher(Constant.Path.ADMIN_EDIT_BRAND).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String BRA_NAME = request.getParameter("bra_name");
        if (BRA_NAME != null && !BRA_NAME.trim().isEmpty()) {
            brand.setBRA_NAME(BRA_NAME);
        }

        String BRA_DES = request.getParameter("cat_des");
        if (BRA_DES != null && !BRA_DES.trim().isEmpty()) {
            brand.setBRA_DES(BRA_DES);
        }

        brandService.edit(brand);
        response.sendRedirect(request.getContextPath() + "/admin/brand/edit?id=" + BRA_ID);
    }
}
