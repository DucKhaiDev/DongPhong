package Controller.Client;

import Entity.Brand;
import Entity.Category;
import Entity.Customer;
import Services.deploy.BrandService;
import Services.deploy.CategoryService;
import Services.deploy.CustomerService;
import Tools.ReleaseMemory;
import Util.Constant;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "WelcomeController", value = "/welcome")
public class WelcomeController extends HttpServlet {
    private final CategoryService categoryService = new CategoryService();
    private final BrandService brandService = new BrandService();
    private final CustomerService customerService = new CustomerService();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResponsiveToInterruption deleteUnusedImg = new ResponsiveToInterruption();
        deleteUnusedImg.start();
        deleteUnusedImg.interrupt();

        ServletContext context = request.getServletContext();

        HttpSession session = request.getSession();
        //Nếu customer không tồn tại
        if (session != null && session.getAttribute("customer") == null) {
            //Kiểm tra cookie, nếu có customer thì sử dụng
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                boolean existed = false;
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("customer")) {
                        Customer customer = customerService.getCustomer(cookie.getValue());
                        session.setAttribute("customer", customer);
                        existed = true;
                        break;
                    }
                }

                //Nếu trong cookie không có customer
                if (!existed) {
                    //Tạo customer mới
                    Customer customer = new Customer();
                    String CUS_ID = UUID.randomUUID().toString();
                    customer.setCUS_ID(CUS_ID);
                    customerService.insert(customer);
                    session.setAttribute("customer", customer);

                    //Lưu customer vào cookie
                    Cookie cookie = new Cookie("customer", CUS_ID);
                    cookie.setMaxAge(3 * 24 * 60 * 60); //Lưu trong 3 ngày
                    response.addCookie(cookie);
                }
            }
        }

        List<Category> lvrCategories = categoryService.getCategoryByRoom("LVR");
        context.setAttribute("lvrCategories", lvrCategories);
        List<Category> kitCategories = categoryService.getCategoryByRoom("KIT");
        context.setAttribute("kitCategories", kitCategories);
        List<Category> bedCategories = categoryService.getCategoryByRoom("BED");
        context.setAttribute("bedCategories", bedCategories);
        List<Category> offCategories = categoryService.getCategoryByRoom("OFF");
        context.setAttribute("offCategories", offCategories);
        List<Category> altCategories = categoryService.getCategoryByRoom("ALT");
        context.setAttribute("altCategories", altCategories);

        List<Brand> brands = brandService.getAll();
        context.setAttribute("brands", brands);

        request.getRequestDispatcher(Constant.Path.HOME).forward(request, response);
    }

    static class ResponsiveToInterruption extends Thread {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                ReleaseMemory.deleteUnusedImg();
            }
        }
    }
}
