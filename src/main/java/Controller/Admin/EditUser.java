package Controller.Admin;

import Entity.User;
import Util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@WebServlet(name = "EditUser", value = "/admin/user/edit")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class EditUser extends HttpServlet {
    private User user;
    private String userId;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userId = request.getParameter("id");

        user = Constant.Service.USER_SERVICE.getUser(userId);
        request.setAttribute("user", user);

        request.getRequestDispatcher(Constant.Path.ADMIN_EDIT_USER).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        if (firstName != null && !firstName.trim().isEmpty()) {
            user.setFirstName(firstName);
        }

        String lastName = request.getParameter("lastName");
        if (lastName != null && !lastName.trim().isEmpty()) {
            user.setLastName(lastName);
        }

        String password = request.getParameter("password");
        if (password != null && !password.trim().isEmpty()) {
            user.setPassword(password);
        }

        String email = request.getParameter("email");
        if (email != null && !email.trim().isEmpty()) {
            user.setEmail(email);
        }

        String address = request.getParameter("address");
        if (address != null && !address.trim().isEmpty()) {
            user.setAddress(address);
        }

        String phone = request.getParameter("phone");
        if (phone != null && !phone.trim().isEmpty()) {
            user.setPhone(phone);
        }

        user.setRole(Boolean.parseBoolean(request.getParameter("role")));

        String savePath = Constant.Path.AVATARS;

        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            if (!fileSaveDir.mkdir()) {
                System.out.println("Directory creation failed.");
            }
        }

        String fileName, newName;

        for (Part part : request.getParts()) {
            fileName = extractFileName(part);
            fileName = new File(fileName).getName();

            if (fileName.length() > 0) {
                part.write(savePath + File.separator + fileName);
                newName = UUID.randomUUID() + "." + FilenameUtils.getExtension(fileName);
                renameFile(fileName, newName);
                user.setAvatar(newName);
            }
        }

        Constant.Service.USER_SERVICE.edit(user);

        HttpSession session = request.getSession();
        if (((User) session.getAttribute("account")).getUserId().equals(user.getUserId())) {
            session.setAttribute("account", user);
        }

        response.sendRedirect(request.getContextPath() + "/admin/user/edit?id=" + userId);
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

    private void renameFile(String oldName, String newName) {
        Path file = Paths.get(Constant.Path.AVATARS + File.separator + oldName);
        try {
            Files.move(file, file.resolveSibling(newName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}