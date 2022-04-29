package Controller.Client;

import Entity.Cart;
import Entity.WishList;
import Tools.SendEmail;
import Util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "RegisterController", value = "/register")
public class RegisterController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Constant.Path.REGISTER).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username").toLowerCase();
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeatPassword");
        String email = request.getParameter("email").toLowerCase();

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
        if (Constant.Service.USER_SERVICE.checkExistUsername(username)) {
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
        if (Constant.Service.USER_SERVICE.checkExistEmail(email)) {
            emailMsg = "Email đã tồn tại!";
            request.setAttribute("emailMsg", emailMsg);
            request.getRequestDispatcher(Constant.Path.REGISTER).forward(request, response);
            return;
        }

        if (Constant.Service.USER_SERVICE.register(username, password, email)) {
            //Create wishlist
            String wishListId = "WL_" + username;
            Constant.Service.WISH_LIST_SERVICE.insert(new WishList(wishListId, Constant.Service.USER_SERVICE.getUser(username)));

            //Create cart
            String cartId = username + "-0";
            Constant.Service.CART_SERVICE.insert(new Cart(cartId, Constant.Service.USER_SERVICE.getUser(username)));
            String loginUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/login";
            String content = "<!DOCTYPE html>\n" +
                    "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"utf-8\">\n" +
                    "    <meta content=\"width=device-width\" name=\"viewport\">\n" +
                    "    <meta content=\"IE=edge\" http-equiv=\"X-UA-Compatible\">\n" +
                    "    <meta name=\"x-apple-disable-message-reformatting\">\n" +
                    "    <title>Welcome - [Plain HTML]</title>\n" +
                    "    <link href=\"https://fonts.googleapis.com/css?family=Montserrat:300,500\" rel=\"stylesheet\">\n" +
                    "    <style>\n" +
                    "        html,\n" +
                    "        body {\n" +
                    "            margin: 0 auto !important;\n" +
                    "            padding: 0 !important;\n" +
                    "            height: 100% !important;\n" +
                    "            width: 100% !important;\n" +
                    "        }\n" +
                    "\n" +
                    "        * {\n" +
                    "            -ms-text-size-adjust: 100%;\n" +
                    "            -webkit-text-size-adjust: 100%;\n" +
                    "        }\n" +
                    "\n" +
                    "        div[style*=\"margin: 16px 0\"] {\n" +
                    "            margin: 0 !important;\n" +
                    "        }\n" +
                    "\n" +
                    "        table,\n" +
                    "        td {\n" +
                    "            mso-table-lspace: 0pt !important;\n" +
                    "            mso-table-rspace: 0pt !important;\n" +
                    "        }\n" +
                    "\n" +
                    "        table {\n" +
                    "            border-spacing: 0 !important;\n" +
                    "            border-collapse: collapse !important;\n" +
                    "            table-layout: fixed !important;\n" +
                    "            margin: 0 auto !important;\n" +
                    "        }\n" +
                    "\n" +
                    "        table table table {\n" +
                    "            table-layout: auto;\n" +
                    "        }\n" +
                    "\n" +
                    "        img {\n" +
                    "            -ms-interpolation-mode: bicubic;\n" +
                    "        }\n" +
                    "\n" +
                    "        *[x-apple-data-detectors],\n" +
                    "\n" +
                    "        .x-gmail-data-detectors *,\n" +
                    "        .aBn {\n" +
                    "            border-bottom: 0 !important;\n" +
                    "            cursor: default !important;\n" +
                    "            color: inherit !important;\n" +
                    "            text-decoration: none !important;\n" +
                    "            font-size: inherit !important;\n" +
                    "            font-family: inherit !important;\n" +
                    "            font-weight: inherit !important;\n" +
                    "            line-height: inherit !important;\n" +
                    "        }\n" +
                    "\n" +
                    "        .a6S {\n" +
                    "            display: none !important;\n" +
                    "            opacity: 0.01 !important;\n" +
                    "        }\n" +
                    "\n" +
                    "        img.g-img + div {\n" +
                    "            display: none !important;\n" +
                    "        }\n" +
                    "\n" +
                    "        .button-link {\n" +
                    "            text-decoration: none !important;\n" +
                    "        }\n" +
                    "\n" +
                    "        @media only screen and (min-device-width: 375px) and (max-device-width: 413px) {\n" +
                    "            .email-container {\n" +
                    "                min-width: 375px !important;\n" +
                    "            }\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "    <style>\n" +
                    "        .button-td,\n" +
                    "        .button-a {\n" +
                    "            transition: all 100ms ease-in;\n" +
                    "        }\n" +
                    "\n" +
                    "        .button-td:hover,\n" +
                    "        .button-a:hover {\n" +
                    "            background: #555555 !important;\n" +
                    "            border-color: #555555 !important;\n" +
                    "        }\n" +
                    "\n" +
                    "        @media screen and (max-width: 480px) {\n" +
                    "            .fluid {\n" +
                    "                width: 100% !important;\n" +
                    "                max-width: 100% !important;\n" +
                    "                height: auto !important;\n" +
                    "                margin-left: auto !important;\n" +
                    "                margin-right: auto !important;\n" +
                    "            }\n" +
                    "\n" +
                    "            .stack-column,\n" +
                    "            .stack-column-center {\n" +
                    "                display: block !important;\n" +
                    "                width: 100% !important;\n" +
                    "                max-width: 100% !important;\n" +
                    "                direction: ltr !important;\n" +
                    "            }\n" +
                    "\n" +
                    "            .stack-column-center {\n" +
                    "                text-align: center !important;\n" +
                    "            }\n" +
                    "\n" +
                    "            .center-on-narrow {\n" +
                    "                text-align: center !important;\n" +
                    "                display: block !important;\n" +
                    "                margin-left: auto !important;\n" +
                    "                margin-right: auto !important;\n" +
                    "                float: none !important;\n" +
                    "            }\n" +
                    "\n" +
                    "            table.center-on-narrow {\n" +
                    "                display: inline-block !important;\n" +
                    "            }\n" +
                    "\n" +
                    "            .email-container p {\n" +
                    "                font-size: 17px !important;\n" +
                    "                line-height: 22px !important;\n" +
                    "            }\n" +
                    "        }\n" +
                    "        \n" +
                    "        .im {\n" +
                    "            color: #fff !important;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "\n" +
                    "<body style=\"width: 100%; background-color: #f1f1f1; margin: 0; mso-line-height-rule: exactly;\">\n" +
                    "<div style=\"width: 100%; background: #F1F1F1; text-align: left;\">\n" +
                    "    <div style=\"display:none;font-size:1px;line-height:1px;max-height:0;max-width:0;opacity:0;overflow:hidden;mso-hide:all;font-family: sans-serif;\">\n" +
                    "        Chúc mừng bạn đã đăng ký tài khoản thành công.\n" +
                    "    </div>\n" +
                    "    <div class=\"email-container\" style=\"max-width: 680px; margin: auto;\">\n" +
                    "        <table class=\"email-container\"\n" +
                    "               role=\"presentation\"\n" +
                    "               style=\"max-width: 680px; border-spacing: 0; padding: 0; border: 0; text-align: center; width: 100%\">\n" +
                    "            <tr>\n" +
                    "                <td style=\"background-color: burlywood\">\n" +
                    "                    <table role=\"presentation\" style=\"border-spacing: 0; padding: 0; border: 0; width: 100%\">\n" +
                    "                        <tr>\n" +
                    "                            <td style=\"padding: 30px 40px 30px 40px; text-align: center;\">\n" +
                    "                                <span style=\"color:#fff; font-size: 30px; font-weight: bold;\">DongPhong.store</span>\n" +
                    "                            </td>\n" +
                    "                        </tr>\n" +
                    "                    </table>\n" +
                    "                </td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "                <td style=\"text-align: center; background: url(https://i.imgur.com/fCm75nS.jpg); background-color: #222; vertical-align: top; background-position: center center !important; background-size: cover !important;\">\n" +
                    "                    <div>\n" +
                    "                        <table role=\"presentation\"\n" +
                    "                               style=\"border: 0; padding: 0; border-spacing: 0; text-align: center; width: 100%; max-width:500px; margin: auto;\">\n" +
                    "                            <tr>\n" +
                    "                                <td height=\"60px\" style=\"font-size:20px; line-height:20px;\">&nbsp;</td>\n" +
                    "                            </tr>\n" +
                    "                            <tr>\n" +
                    "                                <td style=\"text-align: center; vertical-align: middle\">\n" +
                    "                                    <table>\n" +
                    "                                        <tr style=\"background-color: rgba(0, 0, 0, 0.69)\">\n" +
                    "                                            <td style=\"vertical-align: top; text-align: center; padding: 20px 0 10px 20px;\">\n" +
                    "                                                <h1 style=\"margin: 0; font-family: 'Montserrat', sans-serif; font-size: 30px; line-height: 36px; color: #fff; font-weight: bold;\">\n" +
                    "                                                    XIN CHÀO " + username + ",\n" +
                    "                                                </h1>\n" +
                    "                                            </td>\n" +
                    "                                        </tr>\n" +
                    "                                        <tr style=\"background-color: rgba(0, 0, 0, 0.69)\">\n" +
                    "                                            <td style=\"vertical-align: top; text-align: center; padding: 10px 20px 15px 20px; font-family: sans-serif; font-size: 15px; line-height: 20px; color: #fff;\">\n" +
                    "                                                <p style=\"margin: 0; text-align: start; color: #fff;\">\n" +
                    "                                                    Tài khoản của bạn tại DongPhong.store đã được tạo thành công! <br>\n" +
                    "                                                    Mật khẩu của bạn là: " + password + " <br>\n" +
                    "                                                    Nếu bạn không thực hiện yêu cầu này, vui lòng bỏ qua email này.\n" +
                    "                                                </p>\n" +
                    "                                            </td>\n" +
                    "                                        </tr>\n" +
                    "                                        <tr>\n" +
                    "                                            <td style=\"vertical-align: top; text-align: center; padding: 15px 0 60px 0;\">\n" +
                    "                                                <div>\n" +
                    "                                                    <table class=\"center-on-narrow\" role=\"presentation\"\n" +
                    "                                                           style=\"text-align: center; border-spacing: 0; padding: 0;\">\n" +
                    "                                                        <tr>\n" +
                    "                                                            <td class=\"button-td\"\n" +
                    "                                                                style=\"border-radius: 50px; background: burlywood; text-align: center;\">\n" +
                    "                                                                <a class=\"button-a\"\n" +
                    "                                                                   href=\"" + loginUrl + "\"\n" +
                    "                                                                   style=\"background: burlywood; border: 15px solid burlywood; font-family: 'Montserrat', sans-serif; font-size: 14px; line-height: 1.1; text-align: center; text-decoration: none; display: block; border-radius: 50px; font-weight: bold;\">\n" +
                    "                                                                    <span class=\"button-link\" style=\"color:#ffffff;\">&nbsp;&nbsp;&nbsp;&nbsp;ĐĂNG NHẬP NGAY&nbsp;&nbsp;&nbsp;&nbsp;</span>\n" +
                    "                                                                </a>\n" +
                    "                                                            </td>\n" +
                    "                                                        </tr>\n" +
                    "                                                    </table>\n" +
                    "                                                </div>\n" +
                    "                                            </td>\n" +
                    "                                        </tr>\n" +
                    "                                    </table>\n" +
                    "                                </td>\n" +
                    "                            </tr>\n" +
                    "                            <tr>\n" +
                    "                                <td height=\"20\" style=\"font-size:20px; line-height:20px;\">&nbsp;</td>\n" +
                    "                            </tr>\n" +
                    "                        </table>\n" +
                    "                    </div>\n" +
                    "                </td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "                <td style=\"background-color: #fff\">\n" +
                    "                    <table role=\"presentation\" style=\"border-spacing: 0; padding: 0; border: 0; width: 100%\">\n" +
                    "                        <tr>\n" +
                    "                            <td style=\"padding: 40px 40px 20px 40px; text-align: left;\">\n" +
                    "                                <h1 style=\"margin: 0; font-family: 'Montserrat', sans-serif; font-size: 20px; line-height: 26px; color: #333333; font-weight: bold;\">\n" +
                    "                                    TÀI KHOẢN CỦA BẠN HIỆN ĐÃ ĐƯỢC KÍCH HOẠT\n" +
                    "                                </h1>\n" +
                    "                            </td>\n" +
                    "                        </tr>\n" +
                    "                        <tr>\n" +
                    "                            <td style=\"padding: 0 40px 20px 40px; font-family: sans-serif; font-size: 15px; line-height: 20px; color: #555555; text-align: left; font-weight:bold;\">\n" +
                    "                                <p style=\"margin: 0;\">Cảm ơn bạn đã lựa chọn dịch vụ của chúng tôi.</p>\n" +
                    "                            </td>\n" +
                    "                        </tr>\n" +
                    "                        <tr>\n" +
                    "                            <td style=\"padding: 0 40px 20px 40px; font-family: sans-serif; font-size: 15px; line-height: 20px; color: #555555; text-align: left; font-weight:normal;\">\n" +
                    "                                <p style=\"margin: 0;\">\n" +
                    "                                    Từ bây giờ bạn sẽ nhận được:\n" +
                    "                                    <br>\n" +
                    "                                <ul>\n" +
                    "                                    <li>Tin tức về các sản phẩm mới ra mắt của Đông Phong</li>\n" +
                    "                                    <li>Tin tức về các khuyến mãi mới nhất</li>\n" +
                    "                                    <li>Thông báo về các ưu đãi độc quyền</li>\n" +
                    "                                    <li>\n" +
                    "                                        <b>\n" +
                    "                                            Đặc biệt, bạn được giảm giá trực tiếp 5% trong lần mua hàng đầu tiên,\n" +
                    "                                            chỉ cần sử dụng mã giảm giá này khi thanh toán: CHAOMUNG.\n" +
                    "                                        </b>\n" +
                    "                                    </li>\n" +
                    "                                </ul>\n" +
                    "                                Muốn nhận thêm nhiều thông tin hơn? Theo dõi chúng tôi trên Fanpage: <a\n" +
                    "                                    href=\"https://www.facebook.com/DoGoCaoCapDongPhong/\">Đồ gỗ Cao cấp Đông Phong</a>\n" +
                    "                                </p>\n" +
                    "                            </td>\n" +
                    "                        </tr>\n" +
                    "                    </table>\n" +
                    "                </td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "                <td style=\"background-color: burlywood\">\n" +
                    "                    <table role=\"presentation\" style=\"border-spacing: 0; padding: 0; border: 0; width: 100%\">\n" +
                    "                        <tr>\n" +
                    "                            <td style=\"padding: 40px 40px 5px 40px; text-align: center;\">\n" +
                    "                                <h1 style=\"margin: 0; font-family: 'Montserrat', sans-serif; font-size: 18px; line-height: 24px; color: #fff; font-weight: bold;\">\n" +
                    "                                    PHẢN HỒI CỦA BẠN LÀ ĐỘNG LỰC ĐỂ CHÚNG TÔI PHÁT TRIỂN TỐT HƠN\n" +
                    "                                </h1>\n" +
                    "                            </td>\n" +
                    "                        </tr>\n" +
                    "                        <tr>\n" +
                    "                            <td style=\"padding: 0 40px 20px 40px; font-family: sans-serif; font-size: 17px; line-height: 23px; color: #fff; text-align: center; font-weight:normal;\">\n" +
                    "                                <p style=\"margin: 0;\">Cho chúng tôi biết bạn nghĩ gì về sản phẩm của chúng tôi</p>\n" +
                    "                            </td>\n" +
                    "                        </tr>\n" +
                    "                        <tr>\n" +
                    "                            <td style=\"vertical-align: middle; text-align: center; padding: 0 20px 40px 20px;\">\n" +
                    "                                <table class=\"center-on-narrow\"\n" +
                    "                                       role=\"presentation\"\n" +
                    "                                       style=\"text-align: center; border-spacing: 0; padding: 0; border: 0\">\n" +
                    "                                    <tr>\n" +
                    "                                        <td class=\"button-td\"\n" +
                    "                                            style=\"border-radius: 50px; background: #ffffff; text-align: center;\">\n" +
                    "                                            <a class=\"button-a\"\n" +
                    "                                               href=\"mailto:duckhaidev@gmail.com\"\n" +
                    "                                               style=\"background: #ffffff; border: 15px solid #ffffff; font-family: 'Montserrat', sans-serif; font-size: 14px; line-height: 1.1; text-align: center; text-decoration: none; display: block; border-radius: 50px; font-weight: bold;\">\n" +
                    "                                                <span class=\"button-link\" style=\"color: burlywood;\">&nbsp;&nbsp;&nbsp;&nbsp;GỬI PHẢN HỒI&nbsp;&nbsp;&nbsp;&nbsp;</span>\n" +
                    "                                            </a>\n" +
                    "                                        </td>\n" +
                    "                                    </tr>\n" +
                    "                                </table>\n" +
                    "                            </td>\n" +
                    "                        </tr>\n" +
                    "                    </table>\n" +
                    "                </td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "                <td style=\"background-color: #fff\">\n" +
                    "                    <table role=\"presentation\" style=\"border-spacing: 0; padding: 0; border: 0; width: 100%\"><br>\n" +
                    "                        <tr>\n" +
                    "                            <td style=\"text-align: center\"><img\n" +
                    "                                    alt=\"\" height=\"37\"\n" +
                    "                                    src=\"https://img.icons8.com/dusk/64/000000/ms-share-point.png\"\n" +
                    "                                    style=\"display: block; border: 0;\" width=\"37\"/></td>\n" +
                    "                        </tr>\n" +
                    "                        <tr>\n" +
                    "                            <td style=\"text-align: center; font-family: sans-serif; font-size: 14px; font-weight: 400; line-height: 24px; padding: 5px 0 10px 0;\">\n" +
                    "                                <b style=\"font-size: 14px; line-height: 18px; color: #333333;\">\n" +
                    "                                    DongPhong.store\n" +
                    "                                    <br>\n" +
                    "                                    Chợ Chục, Phong Nẫm, Đông Phong, Yên Phong, Bắc Ninh\n" +
                    "                                </b>\n" +
                    "                            </td>\n" +
                    "                        </tr>\n" +
                    "                        <tr>\n" +
                    "                            <td style=\"padding: 0 40px 40px 40px; font-family: sans-serif; font-size: 12px; line-height: 18px; color: #666666; text-align: center; font-weight:normal;\">\n" +
                    "                                <p style=\"margin: 0;\">\n" +
                    "                                    Bản quyền " + new SimpleDateFormat("yyyy").format(new Date()) + " &copy;<b>DongPhong.store</b>\n" +
                    "                                </p>\n" +
                    "                            </td>\n" +
                    "                        </tr>\n" +
                    "                    </table>\n" +
                    "                </td>\n" +
                    "            </tr>\n" +
                    "        </table>\n" +
                    "    </div>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>";
            SendEmail.sendEmail(email, "DongPhong.store", content);

            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals(Constant.COOKIE_USERNAME) || cookie.getName().equals(Constant.COOKIE_PASSWORD)) {
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                }
            }

            request.setAttribute("registerSuccess", "Đăng ký thành công!");
            request.getRequestDispatcher(Constant.Path.LOGIN).forward(request, response);
        } else {
            rgtMsg = "Lỗi hệ thống!";
            request.setAttribute("rgtMsg", rgtMsg);
            request.getRequestDispatcher(Constant.Path.REGISTER).forward(request, response);
        }
    }
}