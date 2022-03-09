package Controller.Admin;

import Services.deploy.BrandService;
import Util.Constant;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "BrandController", value = "/admin/brand")
public class BrandController extends HttpServlet {
    private final BrandService brandService = new BrandService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Entity.Brand> brands = brandService.getAll();
        request.setAttribute("brands", brands);
        request.getRequestDispatcher(Constant.Path.ADMIN_BRAND).forward(request, response);
    }
}
