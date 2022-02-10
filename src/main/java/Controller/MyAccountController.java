package Controller;

import Entity.User;
import ServicesImpl.UserServiceImpl;
import Util.Constant;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.File;
import java.io.IOException;

@WebServlet(name = "MyAccountController", urlPatterns = "/member/my-account")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class MyAccountController extends HttpServlet {
    private UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Constant.Path.MYACCOUNT).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");

        String update_firstname = request.getParameter("update_firstname");
        if (update_firstname != null && !update_firstname.trim().isEmpty()) {
            user.setFIRSTNAME(update_firstname);
        }

        String update_lastname = request.getParameter("update_lastname");
        if (update_lastname != null && !update_lastname.trim().isEmpty()) {
            user.setLASTNAME(update_lastname);
        }

        String update_email = request.getParameter("update_email");
        if (update_email != null && !update_email.trim().isEmpty()) {
            user.setEMAIL(update_email);
        }

        String update_address = request.getParameter("update_address");
        if (update_address != null && !update_address.trim().isEmpty()) {
            user.setADDRESS(update_address);
        }

        String update_phone = request.getParameter("update_phone");
        if (update_phone != null && !update_phone.trim().isEmpty()) {
            user.setPHONE(update_phone);
        }

        String savePath = Constant.Path.AVATARS;

        File fileSaveDir = new File(savePath);
        if (!fileSaveDir .exists()) {
            fileSaveDir.mkdir();
        }

        String fileName = "";

        for (Part part : request.getParts()) {
            fileName = extractFileName(part);
            fileName = new File(fileName).getName();

            if (fileName.length() > 0) {
                part.write(savePath + File.separator + fileName);
                user.setAVATAR(fileName);
            }
        }

        userService.edit(user);
        session.setAttribute("account", user);
        response.sendRedirect(request.getContextPath() + "/member/my-account");
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }
}