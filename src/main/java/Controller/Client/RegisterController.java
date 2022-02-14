package Controller.Client;

import ServicesImpl.UserServiceImpl;
import Tools.SendEmail;
import Util.Constant;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "RegisterController", value = "/register")
public class RegisterController extends HttpServlet {
    private UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Constant.Path.REGISTER).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repeat_password = request.getParameter("repeat_password");
        String email = request.getParameter("email");

        String rgtMsg = "";
        String usnMsg = "";
        String pswMsg = "";
        String rpswMsg = "";
        String emailMsg = "";

        String regexUsername = "^[A-Za-z][A-Za-z0-9_.-]{5,14}$";
        /*
        Kiểm tra tên đăng nhập:
            + Tên đăng nhập phải có độ dài 6-15 ký tự
            + Tên đăng nhập phải bắt đầu bằng chữ cái
            + Có thể chứa chữ in hoa A-Z, chữ in thường a-z, chữ số 0-9 và các ký tự: -_.
         */
        //Kiểm tra độ dài tên đăng nhập
        if (username.length() < 6 || username.length() > 15) {
            usnMsg = "Tên đăng nhập phải có 6-15 ký tự";
            request.setAttribute("usnMsg", usnMsg);
            request.getRequestDispatcher(Constant.Path.REGISTER).forward(request, response);
            return;
        }

        //Kiểm tra định dạng tên đăng nhập
        if (!username.matches(regexUsername)) {
            usnMsg = "Tên đăng nhập chỉ chứa các kí tự cho phép gồm: " +
                    "chữ in hoa, chữ in thường, chữ số (a-z, A-Z, 0-9), dấu gạch dưới, dấu gạch ngang và dấu chấm. " +
                    "Tên đăng nhập phải bắt đầu bằng chữ cái.";
            request.setAttribute("usnMsg", usnMsg);
            request.getRequestDispatcher(Constant.Path.REGISTER).forward(request, response);
            return;
        }

        //Kiểm tra sự tồn tại tên đăng nhập
        if (userService.checkExistUSERNAME(username)) {
            usnMsg = "Tên đăng nhập đã tồn tại!";
            request.setAttribute("usnMsg", usnMsg);
            request.getRequestDispatcher(Constant.Path.REGISTER).forward(request, response);
            return;
        }

        String regexPassword = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*#?&]{8,32}$";
        /*
        Kiểm tra mật khẩu:
            + Mật khẩu phải có độ dài 8-32 ký tự
            + Mật khẩu phải chứa ít nhất 1 chữ cái a-z hoặc A-Z
            + Mật khẩu phải chứa ít nhất 1 chữ số 0-9
            + Chỉ cho phép dùng các ký tự đặc biệt: @ $ ! % * # ?
         */
        //Kiểm tra độ dài mật khẩu
        if (password.length() < 8 || password.length() > 32 || password.equals(username)) {
            pswMsg = "Mật khẩu phải có 8-32 ký tự và không được trùng với tên đăng nhập";
            request.setAttribute("pswMsg", pswMsg);
            request.getRequestDispatcher(Constant.Path.REGISTER).forward(request, response);
            return;
        }

        //Kiểm tra định dạng mật khẩu
        if (!password.matches(regexPassword)) {
            pswMsg = "Mật khẩu phải đảm bảo: " +
                    "có ít nhất 1 chữ cái (a-z hoặc A-Z) và 1 chữ số, " +
                    "và chỉ cho phép chứa các ký tự đặc biệt: @$!%*#?&";
            request.setAttribute("pswMsg", pswMsg);
            request.getRequestDispatcher(Constant.Path.REGISTER).forward(request, response);
            return;
        }

        //Kiểm tra đối chiếu nhập lại mật khẩu
        if (!password.equals(repeat_password)) {
            rpswMsg = "Mật khẩu không trùng khớp!";
            request.setAttribute("rpswMsg", rpswMsg);
            request.getRequestDispatcher(Constant.Path.REGISTER).forward(request, response);
            return;
        }

        //Kiểm tra sự tồn tại email
        if (userService.checkExistEMAIL(email)) {
            emailMsg = "Email đã tồn tại!";
            request.setAttribute("emailMsg", emailMsg);
            request.getRequestDispatcher(Constant.Path.REGISTER).forward(request, response);
            return;
        }

        if (userService.register(username, password, email)) {
            String text = "Thân chào " + username + ",\n" +
                    "Chúc mừng bạn đã đăng ký tài khoản thành công!";
            SendEmail.sendEmail(email, "Dong Phong Furniture", text);
            response.sendRedirect("./login");
        } else {
            rgtMsg = "Lỗi hệ thống";
            request.setAttribute("rgtMsg", rgtMsg);
            request.getRequestDispatcher(Constant.Path.REGISTER).forward(request, response);
        }
    }
}
