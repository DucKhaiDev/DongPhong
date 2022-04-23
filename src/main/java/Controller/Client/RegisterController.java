package Controller.Client;

import Entity.Cart;
import Entity.WishList;
import Services.deploy.CartService;
import Services.deploy.UserService;
import Services.deploy.WishListService;
import Tools.SendEmail;
import Util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "RegisterController", value = "/register")
public class RegisterController extends HttpServlet {
    private final UserService userService = new UserService();
    private final WishListService wishListService = new WishListService();
    private final CartService cartService = new CartService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Constant.Path.REGISTER).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeatPassword");
        String email = request.getParameter("email");

        String rgtMsg;
        String usnMsg;
        String pswMsg;
        String rpswMsg;
        String emailMsg;

        String regexUsername = "^[A-Za-z][A-Za-z0-9_.-]{5,14}$";
        /*
        Check username:
            + Username must be between 6-15 characters
            + Username must start with a letter
            + Username can contain A-Z, a-z, 0-9 and characters: -_.
         */
        //Check username length
        if (username.length() < 6 || username.length() > 15) {
            usnMsg = "Tên đăng nhập phải có độ dài 6-15 ký tự";
            request.setAttribute("usnMsg", usnMsg);
            request.getRequestDispatcher(Constant.Path.REGISTER).forward(request, response);
            return;
        }

        //Check username format
        if (!username.matches(regexUsername)) {
            usnMsg = "Tên đăng nhập chỉ chứa các kí tự cho phép gồm: " +
                    "chữ in hoa, chữ in thường, chữ số (a-z, A-Z, 0-9), dấu gạch dưới, dấu gạch ngang và dấu chấm. " +
                    "Tên đăng nhập phải bắt đầu bằng chữ cái.";
            request.setAttribute("usnMsg", usnMsg);
            request.getRequestDispatcher(Constant.Path.REGISTER).forward(request, response);
            return;
        }

        //Check if the username already exists
        if (userService.checkExistUsername(username)) {
            usnMsg = "Tên đăng nhập đã tồn tại!";
            request.setAttribute("usnMsg", usnMsg);
            request.getRequestDispatcher(Constant.Path.REGISTER).forward(request, response);
            return;
        }

        String regexPassword = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*#?&]{8,32}$";
        /*
        Check password:
            + Password must be between 8-32 characters
            + Password must contain at least 1 letter a-z or A-Z
            + Password must contain at least 1 digit 0-9
            + Only special characters as: @ $ ! % * # ?
         */
        //Check password length
        if (password.length() < 8 || password.length() > 32 || password.equals(username)) {
            pswMsg = "Mật khẩu phải có độ dài 8-32 ký tự và không được trùng với tên đăng nhập";
            request.setAttribute("pswMsg", pswMsg);
            request.getRequestDispatcher(Constant.Path.REGISTER).forward(request, response);
            return;
        }

        //Check password format
        if (!password.matches(regexPassword)) {
            pswMsg = "Mật khẩu phải đảm bảo: " +
                    "có ít nhất 1 chữ cái (a-z hoặc A-Z) và 1 chữ số, " +
                    "và chỉ cho phép chứa các ký tự đặc biệt: @$!%*#?&";
            request.setAttribute("pswMsg", pswMsg);
            request.getRequestDispatcher(Constant.Path.REGISTER).forward(request, response);
            return;
        }

        //Check repeat password
        if (!password.equals(repeatPassword)) {
            rpswMsg = "Mật khẩu không trùng khớp!";
            request.setAttribute("rpswMsg", rpswMsg);
            request.getRequestDispatcher(Constant.Path.REGISTER).forward(request, response);
            return;
        }

        //Check if the email already exists
        if (userService.checkExistEmail(email)) {
            emailMsg = "Email đã tồn tại!";
            request.setAttribute("emailMsg", emailMsg);
            request.getRequestDispatcher(Constant.Path.REGISTER).forward(request, response);
            return;
        }

        if (userService.register(username, password, email)) {
            //Create wishlist
            String wishListId = "WL_" + username;
            wishListService.insert(new WishList(wishListId, userService.getUser(username)));

            //Create cart
            String cartId = username + "-0";
            cartService.insert(new Cart(cartId, userService.getUser(username)));

            String text = "Thân chào " + username + ",\n" +
                    "Chúc mừng bạn đã đăng ký tài khoản thành công!";
            SendEmail.sendEmail(email, "Dong Phong Furniture", text);
            response.sendRedirect("./login");
        } else {
            rgtMsg = "Lỗi hệ thống!";
            request.setAttribute("rgtMsg", rgtMsg);
            request.getRequestDispatcher(Constant.Path.REGISTER).forward(request, response);
        }
    }
}
