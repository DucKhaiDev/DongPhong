package Controller.Client;

import Controller.WaitingController;
import Entity.User;
import Tools.SendEmail;
import Util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@WebServlet(name = "ForgetPassword", value = "/forget-password")
public class ForgetPassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Constant.Path.FORGET_PASSWORD).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        if (Constant.Service.USER_SERVICE.getUser(username).getUserId() == null) {
            request.setAttribute("message", "Email/Tên đăng nhập không tồn tại!");
            request.getRequestDispatcher(Constant.Path.FORGET_PASSWORD).forward(request, response);
            return;
        }

        User user = Constant.Service.USER_SERVICE.getUser(username);
        String newPassword = Integer.toString(10000000 + new Random().nextInt(90000000));

        String content = "<!DOCTYPE html>\n" +
                "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta content=\"width=device-width\" name=\"viewport\">\n" +
                "    <meta content=\"IE=edge\" http-equiv=\"X-UA-Compatible\">\n" +
                "    <meta name=\"x-apple-disable-message-reformatting\">\n" +
                "    <title>Welcome to DongPhong</title>\n" +
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
                "\n" +
                "        .im {\n" +
                "            color: #fff !important;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body style=\"width: 100%; background-color: #f1f1f1; margin: 0; mso-line-height-rule: exactly;\">\n" +
                "<div style=\"width: 100%; background: #F1F1F1; text-align: left;\">\n" +
                "    <div style=\"display:none;font-size:1px;line-height:1px;max-height:0;max-width:0;opacity:0;overflow:hidden;mso-hide:all;font-family: sans-serif;\">\n" +
                "        Chúng tôi đã thực hiện yêu cầu quên mật khẩu của bạn.\n" +
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
                "                <td style=\"text-align: center; background: url(https://i.imgur.com/fCKKYn1.jpg); background-color: #222; vertical-align: top; background-position: center center !important; background-size: cover !important;\">\n" +
                "                    <div>\n" +
                "                        <table role=\"presentation\"\n" +
                "                               style=\"border: 0; padding: 0; border-spacing: 0; text-align: center; width: 100%; max-width:500px; margin: auto;\">\n" +
                "                            <tr>\n" +
                "                                <td height=\"60px\" style=\"font-size:20px; line-height:20px;\">&nbsp;</td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td style=\"text-align: center; vertical-align: middle\">\n" +
                "                                    <table>\n" +
                "                                        <tr style=\"background-color: rgba(0, 0, 0, 0.5)\">\n" +
                "                                            <td style=\"vertical-align: top; text-align: center; padding: 20px 0 10px 20px;\">\n" +
                "                                                <h1 style=\"margin: 0; font-family: 'Montserrat', sans-serif; font-size: 30px; line-height: 36px; color: #fff; font-weight: bold;\">\n" +
                "                                                    XIN CHÀO " + WaitingController.displayName(user) + ",\n" +
                "                                                </h1>\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                        <tr style=\"background-color: rgba(0, 0, 0, 0.5)\">\n" +
                "                                            <td style=\"vertical-align: top; text-align: center; padding: 10px 20px 15px 20px; font-family: sans-serif; font-size: 15px; line-height: 20px; color: #fff;\">\n" +
                "                                                <p style=\"margin: 0; text-align: start; color: #fff;\">\n" +
                "                                                    Chúng tôi đã nhận được yêu cầu cấp lại mật khẩu của bạn! <br>\n" +
                "                                                    Mật khẩu mới của bạn là: <b>" + newPassword + "</b> <br>\n" +
                "                                                    Nếu bạn không thực hiện yêu cầu này, vui lòng thông báo cho chúng tôi biết.\n" +
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
                "                                                                   href=\"https://dongphong.store/login\"\n" +
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
        if (SendEmail.sendEmail(user.getEmail(), "DongPhong.store", content)) {
            user.setPassword(newPassword);
            Constant.Service.USER_SERVICE.edit(user);
            request.setAttribute("message", "Chúng tôi đã gửi mật khẩu mới tới email đã đăng ký của bạn. Vui lòng kiểm tra email.");
        } else {
            request.setAttribute("message", "Có lỗi xảy ra, chúng tôi sẽ liên hệ lại với bạn sau!");
        }

        request.getRequestDispatcher(Constant.Path.FORGET_PASSWORD).forward(request, response);
    }
}