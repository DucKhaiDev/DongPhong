package Controller.Admin;

import Controller.Client.Checkout;
import Controller.Client.LoginController;
import Controller.WaitingController;
import Entity.*;
import Tools.SendEmail;
import Util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@WebServlet(name = "AddOrder", value = "/admin/order/add")
public class AddOrder extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!LoginController.checkLoginAdmin(request, response)) {
            return;
        }

        List<Voucher> availableVoucher = Constant.Service.VOUCHER_SERVICE.getAvailableVoucher(((Cart) request.getSession().getAttribute("cart")).getCartId());
        availableVoucher.add(Constant.Service.VOUCHER_SERVICE.getVoucher("CHAOMUNG"));
        request.setAttribute("availableVoucher", availableVoucher);
        request.getRequestDispatcher(Constant.Path.ADMIN_ADD_ORDER).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order order = new Order();
        order.setOrderSubTotal(new BigDecimal(request.getParameter("subTotal")));
        order.setOrderDiscount(new BigDecimal(request.getParameter("discount")));
        order.setOrderShipping(new BigDecimal(request.getParameter("shipping")));
        order.setOrderTax(new BigDecimal(request.getParameter("vat")));
        order.setOrderTotal(new BigDecimal(request.getParameter("total")));
        User user = (User) request.getSession().getAttribute("account");
        order.setUser(user);
        order.setRecipientName(request.getParameter("fullName"));
        String recAddress = request.getParameter("recaddress") + ", "
                + request.getParameter("selectedWard") + ", "
                + request.getParameter("selectedDistrict") + ", "
                + request.getParameter("selectedProvince") + ".";
        order.setRecipientAddress(recAddress);
        order.setRecipientPhone(request.getParameter("phone"));
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        order.setOrderSumProduct(((List<?>) request.getSession().getAttribute("cartItems")).size());
        order.setCart((Cart) request.getSession().getAttribute("cart"));
        Payment payment = Constant.Service.PAYMENT_SERVICE.getPayment(request.getParameter("paymentMethod"));
        order.setPayment(payment);
        order.setOrderStatus((byte) 1);

        Constant.Service.ORDER_SERVICE.insert(order);

        Order order_rv = Constant.Service.ORDER_SERVICE.getNewestOrder();
        List<CartItem> cartItems_rv = Constant.Service.CART_ITEM_SERVICE.getItemByCart(order.getCart().getCartId());

        //Send email
        Checkout checkout = new Checkout();
        String content = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>????n ?????t h??ng</title>\n" +
                "    <meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\"/>\n" +
                "    <meta content=\"width=device-width, initial-scale=1\" name=\"viewport\">\n" +
                "    <meta content=\"IE=edge\" http-equiv=\"X-UA-Compatible\"/>\n" +
                "    <style type=\"text/css\">\n" +
                "        body,\n" +
                "        table,\n" +
                "        td,\n" +
                "        a {\n" +
                "            -webkit-text-size-adjust: 100%;\n" +
                "            -ms-text-size-adjust: 100%;\n" +
                "        }\n" +
                "\n" +
                "        table,\n" +
                "        td {\n" +
                "            mso-table-lspace: 0pt;\n" +
                "            mso-table-rspace: 0pt;\n" +
                "        }\n" +
                "\n" +
                "        img {\n" +
                "            -ms-interpolation-mode: bicubic;\n" +
                "        }\n" +
                "\n" +
                "        img {\n" +
                "            border: 0;\n" +
                "            height: auto;\n" +
                "            line-height: 100%;\n" +
                "            outline: none;\n" +
                "            text-decoration: none;\n" +
                "        }\n" +
                "\n" +
                "        table {\n" +
                "            border-collapse: collapse !important;\n" +
                "        }\n" +
                "\n" +
                "        body {\n" +
                "            height: 100% !important;\n" +
                "            margin: 0 !important;\n" +
                "            padding: 0 !important;\n" +
                "            width: 100% !important;\n" +
                "        }\n" +
                "\n" +
                "        a[x-apple-data-detectors] {\n" +
                "            color: inherit !important;\n" +
                "            text-decoration: none !important;\n" +
                "            font-size: inherit !important;\n" +
                "            font-family: inherit !important;\n" +
                "            font-weight: inherit !important;\n" +
                "            line-height: inherit !important;\n" +
                "        }\n" +
                "\n" +
                "        @media screen and (max-width: 480px) {\n" +
                "            .mobile-hide {\n" +
                "                display: none !important;\n" +
                "            }\n" +
                "\n" +
                "            .mobile-center {\n" +
                "                text-align: center !important;\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        div[style*=\"margin: 16px 0;\"] {\n" +
                "            margin: 0 !important;\n" +
                "        }\n" +
                "    </style>\n" +
                "\n" +
                "<body bgcolor=\"#eeeeee\" style=\"margin: 0 !important; padding: 0 !important; background-color: #eeeeee;\">\n" +
                "<div style=\"display:none;font-size:1px;line-height:1px;max-height:0;max-width:0;opacity:0;overflow:hidden;mso-hide:all;font-family: sans-serif;\">\n" +
                "    ????n h??ng c???a b???n ???? ???????c ?????t th??nh c??ng.\n" +
                "</div>\n" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "    <tr>\n" +
                "        <td align=\"center\" bgcolor=\"#eeeeee\" style=\"background-color: #eeeeee;\">\n" +
                "            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:600px;\" width=\"100%\">\n" +
                "                <tr>\n" +
                "                    <td align=\"center\" style=\"font-size:0; padding: 35px; background-color: burlywood\" valign=\"top\">\n" +
                "                        <div style=\"display:inline-block; max-width:100%; min-width:100px; vertical-align:top; width:100%;\">\n" +
                "                            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:300px;\"\n" +
                "                                   width=\"100%\">\n" +
                "                                <tr>\n" +
                "                                    <td align=\"center\" class=\"mobile-center\"\n" +
                "                                        style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 36px; font-weight: 800; line-height: 48px;\"\n" +
                "                                        valign=\"middle\">\n" +
                "                                        <h1 style=\"font-size: 36px; font-weight: 800; margin: 0; color: #ffffff;\">\n" +
                "                                            DongPhong.store</h1>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                            </table>\n" +
                "                        </div>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td align=\"center\" bgcolor=\"#ffffff\"\n" +
                "                        style=\"padding: 35px 35px 20px 35px; background-color: #ffffff;\">\n" +
                "                        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:600px;\"\n" +
                "                               width=\"100%\">\n" +
                "                            <tr>\n" +
                "                                <td align=\"center\"\n" +
                "                                    style=\"font-family: 'Montserrat', sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding-top: 25px;\">\n" +
                "                                    <img height=\"120px\" src=\"https://img.icons8.com/ios-filled/150/deb887/ok--v1.png\"\n" +
                "                                         style=\"display: block; border: 0;\" width=\"120px\"/><br>\n" +
                "                                    <h2 style=\"font-size: 30px; line-height: 36px; color: #333333; margin: 0;\"> C???m ??n\n" +
                "                                        b???n ???? l???a ch???n s???n ph???m c???a ch??ng t??i! </h2>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td align=\"left\"\n" +
                "                                    style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding-top: 10px;\">\n" +
                "                                    <p style=\"font-size: 16px; font-weight: 400; line-height: 24px; color: #777777;\">\n" +
                "                                        ????n h??ng c???a b???n s??? ???????c v???n chuy???n trong v??ng 5 - 7 ng??y l??m vi???c (tr??? ng??y\n" +
                "                                        ngh??? v?? l??? T???t). Vui l??ng thanh to??n khi nh???n h??ng. </p>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td align=\"left\" style=\"padding-top: 20px;\">\n" +
                "                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                                        <tr>\n" +
                "                                            <td align=\"left\" bgcolor=\"#eeeeee\" style=\"font-family: 'Montserrat', sans-serif; font-size: 16px; font-weight: bold; line-height: 24px; padding: 10px;\"\n" +
                "                                                width=\"75%\">\n" +
                "                                                ????n h??ng #" + order_rv.getOrderId() + "\n" +
                "                                            </td>\n" +
                "                                            <td align=\"left\" bgcolor=\"#eeeeee\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; line-height: 24px; padding: 10px;\"\n" +
                "                                                width=\"25%\"></td>\n" +
                "                                        </tr>\n" +
                checkout.listProducts(cartItems_rv) +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td align=\"left\" style=\"padding-top: 20px;\">\n" +
                "                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                                        <tr>\n" +
                "                                            <td align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; line-height: 24px; padding: 10px; border-top: 3px solid #eeeeee;\"\n" +
                "                                                width=\"75%\">\n" +
                "                                                T???ng\n" +
                "                                            </td>\n" +
                "                                            <td align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; line-height: 24px; padding: 10px; border-top: 3px solid #eeeeee;\"\n" +
                "                                                width=\"25%\">\n" +
                "                                                " + Constant.NF_DONG.format(order_rv.getOrderSubTotal()) + "\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                        <tr>\n" +
                "                                            <td align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; line-height: 24px; padding: 10px;\"\n" +
                "                                                width=\"75%\">\n" +
                "                                                Gi???m gi??\n" +
                "                                            </td>\n" +
                "                                            <td align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; line-height: 24px; padding: 10px;\"\n" +
                "                                                width=\"25%\">\n" +
                "                                                " + Constant.NF_DONG.format(order_rv.getOrderDiscount()) + "\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                        <tr>\n" +
                "                                            <td align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; line-height: 24px; padding: 10px;\"\n" +
                "                                                width=\"75%\">\n" +
                "                                                Ph?? v???n chuy???n\n" +
                "                                            </td>\n" +
                "                                            <td align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; line-height: 24px; padding: 10px;\"\n" +
                "                                                width=\"25%\">\n" +
                "                                                " + Constant.NF_DONG.format(order_rv.getOrderShipping()) + "\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                        <tr>\n" +
                "                                            <td align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; line-height: 24px; padding: 10px; border-bottom: 3px solid #eeeeee;\"\n" +
                "                                                width=\"75%\">\n" +
                "                                                Thu??? GTGT\n" +
                "                                            </td>\n" +
                "                                            <td align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; line-height: 24px; padding: 10px; border-bottom: 3px solid #eeeeee;\"\n" +
                "                                                width=\"25%\">\n" +
                "                                                " + Constant.NF_DONG.format(order_rv.getOrderTax()) + "\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                        <tr>\n" +
                "                                            <td align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: bold; line-height: 24px; padding: 10px; border-top: 3px solid #eeeeee; border-bottom: 3px solid #eeeeee;\"\n" +
                "                                                width=\"75%\">\n" +
                "                                                T???NG\n" +
                "                                            </td>\n" +
                "                                            <td align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: bold; line-height: 24px; padding: 10px; border-top: 3px solid #eeeeee; border-bottom: 3px solid #eeeeee;\"\n" +
                "                                                width=\"25%\">\n" +
                "                                                " + Constant.NF_DONG.format(order_rv.getOrderTotal()) + "\n" +
                "                                            </td>\n" +
                "                                        </tr>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td align=\"center\" bgcolor=\"#ffffff\" height=\"100%\" style=\"padding: 0 35px 35px 35px; background-color: #ffffff;\"\n" +
                "                        valign=\"top\" width=\"100%\">\n" +
                "                        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:660px;\"\n" +
                "                               width=\"100%\">\n" +
                "                            <tr>\n" +
                "                                <td align=\"center\" style=\"font-size:0;\" valign=\"top\">\n" +
                "                                    <div style=\"display:inline-block; max-width:50%; min-width:240px; vertical-align:top; width:100%;\">\n" +
                "                                        <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:300px;\"\n" +
                "                                               width=\"100%\">\n" +
                "                                            <tr>\n" +
                "                                                <td align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px;\"\n" +
                "                                                    valign=\"top\">\n" +
                "                                                    <p style=\"font-weight: bold\">?????a ch??? nh???n h??ng</p>\n" +
                checkout.splitAddress(order_rv.getRecipientAddress()) +
                "                                                </td>\n" +
                "                                            </tr>\n" +
                "                                        </table>\n" +
                "                                    </div>\n" +
                "                                    <div style=\"display:inline-block; max-width:50%; min-width:240px; vertical-align:top; width:100%;\">\n" +
                "                                        <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:300px;\"\n" +
                "                                               width=\"100%\">\n" +
                "                                            <tr>\n" +
                "                                                <td align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px;\"\n" +
                "                                                    valign=\"top\">\n" +
                "                                                    <p style=\"font-weight: bold; text-align: end;\">Ng??y nh???n h??ng (d??? ki???n)</p>\n" +
                checkout.recipientDate() +
                "                                                </td>\n" +
                "                                            </tr>\n" +
                "                                        </table>\n" +
                "                                    </div>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td align=\"center\" bgcolor=\"#1b9ba3\" style=\" padding: 35px; background-color: burlywood;\">\n" +
                "                        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:600px;\"\n" +
                "                               width=\"100%\">\n" +
                "                            <tr>\n" +
                "                                <td align=\"center\"\n" +
                "                                    style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding-top: 25px;\">\n" +
                "                                    <h2 style=\"font-size: 24px; line-height: 30px; color: #ffffff; margin: 0;\"> Ti???p t???c\n" +
                "                                        mua h??ng ????? nh???n th??m nhi???u ??u ????i h???p d???n. </h2>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td align=\"center\" style=\"padding: 25px 0 15px 0;\">\n" +
                "                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                                        <tr>\n" +
                "                                            <td align=\"center\" bgcolor=\"#66b3b7\" style=\"border-radius: 50px;\"><a\n" +
                "                                                    href=\"https://dongphong.store\" style=\"font-size: 18px; font-family: 'Montserrat', sans-serif; color: burlywood; text-decoration: none; border-radius: 50px; background-color: #fff; padding: 15px 30px; border: 1px solid burlywood; display: block; font-weight: bold\"\n" +
                "                                                    target=\"_blank\">Mua\n" +
                "                                                Ti???p</a></td>\n" +
                "                                        </tr>\n" +
                "                                    </table>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td align=\"center\" bgcolor=\"#ffffff\" style=\"padding: 35px; background-color: #ffffff;\">\n" +
                "                        <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:600px;\"\n" +
                "                               width=\"100%\">\n" +
                "                            <tr>\n" +
                "                                <td align=\"center\"><img height=\"69px\" src=\"https://i.imgur.com/IlrpaXm.png\" style=\"display: block; border: 0;\"\n" +
                "                                                        width=\"69px\"/></td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td align=\"center\"\n" +
                "                                    style=\"font-family: 'Montserrat', sans-serif; font-size: 14px; font-weight: 400; line-height: 24px; padding: 5px 0 10px 0;\">\n" +
                "                                    <p style=\"font-size: 14px; line-height: 20px; color: #333333; font-weight: bold\"> ?????\n" +
                "                                        G??? CAO C???P ????NG PHONG <br> ??/c: Ch??? Ch???c, Phong N???m, ????ng Phong, Y??n Phong, B???c\n" +
                "                                        Ninh <br> S??T: (+84) 98 252 3005 </p>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td align=\"left\"\n" +
                "                                    style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 14px; font-weight: 400; line-height: 24px;\">\n" +
                "                                    <p style=\"font-size: 14px; font-weight: 400; line-height: 20px; color: #777777;\">\n" +
                "                                        N???u b???n kh??ng ????ng k?? t??i kho???n s??? d???ng ?????a ch??? email n??y, xin vui l??ng b??? qua\n" +
                "                                        email n??y, ho???c <a href=\"#\" style=\"color: #777777;\" target=\"_blank\">h???y ????ng\n" +
                "                                        k??</a>. </p>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "        </td>\n" +
                "    </tr>\n" +
                "</table>\n" +
                "</body>\n" +
                "\n" +
                "</html>";
        SendEmail.sendEmail(order.getUser().getEmail(), "DongPhong.store", content);

        //Create new cart
        Checkout.createNewCart(request, user);

        HttpSession session = request.getSession();
        //Minus voucher's quantity
        Voucher usingVoucher = (Voucher) session.getAttribute("usingVoucher");
        if (usingVoucher != null) {
            if (usingVoucher.getVoucherId().equals("CHAOMUNG")) {
                //If member
                if (user.isRole()) {
                    user.setVc_chaomung(true);
                    Constant.Service.USER_SERVICE.edit(user);
                }
            } else {
                usingVoucher.setQuantity(usingVoucher.getQuantity() - 1);
                Constant.Service.VOUCHER_SERVICE.edit(usingVoucher);
            }
        }

        //Remove attributes
        WaitingController.removeAllAttr(session);

        response.sendRedirect(request.getContextPath() + "/admin/order/detail?id=" + (Constant.Service.ORDER_SERVICE.getNewestOrder()).getOrderId());
    }
}