package Controller.Client;

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

@WebServlet(name = "MyAccount", urlPatterns = "/member/my-account")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class MyAccount extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Constant.Path.MY_ACCOUNT).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");

        String firstName = request.getParameter("firstName");
        if (firstName != null && !firstName.trim().isEmpty()) {
            user.setFirstName(firstName);
        }

        String lastName = request.getParameter("lastName");
        if (lastName != null && !lastName.trim().isEmpty()) {
            user.setLastName(lastName);
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

        String savePath = Constant.Path.AVATARS;

        File fileSaveDir = new File(savePath);
        if (!fileSaveDir .exists()) {
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

    private void renameFile(String oldFile, String newFile) {
        Path file = Paths.get(Constant.Path.AVATARS + File.separator + oldFile);
        try {
            Files.move(file, file.resolveSibling(newFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
