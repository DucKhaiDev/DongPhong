package Controller.Admin;

import Services.deploy.VoucherService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DeleteVoucher", value = "/admin/voucher/delete")
public class DeleteVoucher extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        new VoucherService().delete(request.getParameter("id"));
        response.sendRedirect(request.getContextPath() + "/admin/voucher");
    }
}