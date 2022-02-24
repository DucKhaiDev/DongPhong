package Controller.Admin;

import Entity.User;
import Services.deploy.UserService;
import Util.Constant;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@WebServlet(name = "EditUserController", value = "/admin/user/edit")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class EditUserController extends HttpServlet {
    private final UserService userService = new UserService();
    private int USER_ID;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        USER_ID = Integer.parseInt(request.getParameter("id"));
        User user = userService.getUser(USER_ID);
        request.setAttribute("user", user);
        request.getRequestDispatcher(Constant.Path.ADMIN_EDIT_USER).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = userService.getUser(USER_ID);

        String update_firstname = request.getParameter("update_firstname");
        if (update_firstname != null && !update_firstname.trim().isEmpty()) {
            user.setFIRSTNAME(update_firstname);
        }

        String update_lastname = request.getParameter("update_lastname");
        if (update_lastname != null && !update_lastname.trim().isEmpty()) {
            user.setLASTNAME(update_lastname);
        }

        String update_password = request.getParameter("update_password");
        if (update_password != null && !update_password.trim().isEmpty()) {
            user.setPASSWORD(update_password);
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

        user.setROLE(Boolean.parseBoolean(request.getParameter("update_role")));

        String savePath = Constant.Path.AVATARS;

        File fileSaveDir = new File(savePath);
        if (!fileSaveDir .exists()) {
            fileSaveDir.mkdir();
        }

        String fileName, newName;

        for (Part part : request.getParts()) {
            fileName = extractFileName(part);
            fileName = new File(fileName).getName();

            if (fileName.length() > 0) {
                part.write(savePath + File.separator + fileName);
                newName = UUID.randomUUID() + "." + FilenameUtils.getExtension(fileName);
                renameFile(fileName, newName);
                user.setAVATAR(newName);
            }
        }

        userService.edit(user);
        HttpSession session = request.getSession();
        if (((User) session.getAttribute("account")).getUSER_ID() == user.getUSER_ID()) {
            session.setAttribute("account", user);
        }

        response.sendRedirect(request.getContextPath() + "/admin/user/edit?id=" + USER_ID);
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
