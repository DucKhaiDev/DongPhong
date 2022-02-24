package Controller.Admin;

import Services.deploy.BrandService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DeleteBrandController", value = "/admin/brand/delete")
public class DeleteBrandController extends HttpServlet {
    private BrandService brandService = new BrandService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        brandService.delete(request.getParameter("id"));
        response.sendRedirect(request.getContextPath() + "/admin/brand");
    }
}
