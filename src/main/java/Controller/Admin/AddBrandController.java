package Controller.Admin;

import Entity.Brand;
import Services.deploy.BrandService;
import Util.Constant;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "AddBrandController", value = "/admin/brand/add")
public class AddBrandController extends HttpServlet {
    private final BrandService brandService = new BrandService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Constant.Path.ADMIN_ADD_BRAND).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String brandId = request.getParameter("brandId");
        if (brandService.checkExistId(brandId)) {
            String existId = "Mã thương hiệu đã tồn tại!";
            request.setAttribute("existId", existId);
            request.getRequestDispatcher(Constant.Path.ADMIN_ADD_BRAND).forward(request, response);
            return;
        }

        String brandName = request.getParameter("brandName");
        String brandDes = request.getParameter("brandDescription");
        Brand brand = new Brand(brandId, brandName, brandDes);
        brandService.insert(brand);
        response.sendRedirect(request.getContextPath() + "/admin/brand");
    }
}
