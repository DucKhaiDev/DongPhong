package Controller.Client;

import Entity.District;
import Entity.Province;
import Entity.Ward;
import Services.deploy.ProvinceService;
import Util.Constant;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CartDetail", value = "/cart")
public class CartDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Province> provinces = new ProvinceService().getAll();
        request.setAttribute("provinces", provinces);
        List<District> districts = new ArrayList<>();
        request.setAttribute("districts", districts);
        List<Ward> wards = new ArrayList<>();
        request.setAttribute("wards", wards);

        request.getRequestDispatcher(Constant.Path.CART).forward(request, response);
    }
}
