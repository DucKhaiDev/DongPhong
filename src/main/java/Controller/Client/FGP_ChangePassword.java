package Controller.Client;

import Entity.TempLink;
import Entity.User;
import Util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "FGP_ChangePassword", value = "/change-password/access")
public class FGP_ChangePassword extends HttpServlet {
    private TempLink tempLink;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getParameter("token");
        tempLink = Constant.Service.TEMP_LINK_SERVICE.getTempLink(token);

        if (tempLink == null) {
            request.setAttribute("notfound", "Đường dẫn không tồn tại!");
        }

        request.getRequestDispatcher(Constant.Path.FGP_CHANGE_PASSWORD).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newPassword = request.getParameter("newPassword");
        String repeatPassword = request.getParameter("repeatPassword");
        String cpwMsg;

        if (newPassword.trim().isEmpty() || repeatPassword.trim().isEmpty()) {
            cpwMsg = "Vui lòng nhập đầy đủ các mục!";
            request.setAttribute("cpwMsg", cpwMsg);
            request.getRequestDispatcher(Constant.Path.FGP_CHANGE_PASSWORD).forward(request, response);
            return;
        }

        User user = tempLink.getUser();

        if (newPassword.equals(repeatPassword)) {
            user.setPassword(newPassword);
            Constant.Service.USER_SERVICE.edit(user);
            Constant.Service.TEMP_LINK_SERVICE.delete(tempLink.getUUID());
            request.setAttribute("changeSuccess", "Thay đổi mật khẩu thành công!");
            request.getRequestDispatcher(Constant.Path.FGP_CHANGE_PASSWORD).forward(request, response);
        } else {
            String cpw_notmatch = "Không trùng khớp!";
            request.setAttribute("cpw_notmatch", cpw_notmatch);
            request.getRequestDispatcher(Constant.Path.FGP_CHANGE_PASSWORD).forward(request, response);
        }
    }
}