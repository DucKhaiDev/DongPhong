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
    private BrandService brandService = new BrandService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Constant.Path.ADMIN_ADD_BRAND).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String BRA_ID = request.getParameter("bra_id");
        if (brandService.checkExistID(BRA_ID)) {
            String existID = "Mã thương hiệu đã tồn tại!";
            request.setAttribute("existID", existID);
            request.getRequestDispatcher(Constant.Path.ADMIN_ADD_BRAND).forward(request, response);
            return;
        }

        String BRA_NAME = request.getParameter("bra_name");
        String BRA_DES = request.getParameter("bra_des");
        Brand brand = new Brand(BRA_ID, BRA_NAME, BRA_DES);
        brandService.insert(brand);
        response.sendRedirect(request.getContextPath() + "/admin/brand");
    }
}
