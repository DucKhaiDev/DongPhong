<%@ page import="Entity.CartItem" %>
<%@ page import="java.util.List" %>
<%@ page import="Entity.Cart" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.math.RoundingMode" %>
<%@ page import="Entity.Product" %>
<%@ page import="Util.Constant" %>
<%--
  User: duckhaidev
  Date: 4/9/2022
  Time: 12:26 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Dong Phong</title>
    <!-- BOOTSTRAP STYLES-->
    <link href="${pageContext.request.contextPath}/assets/css/bootstrap.css" rel="stylesheet"/>
    <!-- FONTAWESOME STYLES-->
    <link href="${pageContext.request.contextPath}/assets/fonts/fontawesome-pro-5.15.4-web/css/all.min.css"
          rel="stylesheet"/>
    <!-- CUSTOM STYLES-->
    <link href="${pageContext.request.contextPath}/assets/css/custom.css" rel="stylesheet"/>
    <!-- GOOGLE FONTS-->
    <link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'/>
    <%---------------------------------------------------------------------------------------------%>
    <style>
        p.exist-id {
            font-size: 14px;
            line-height: 20px;
            padding-top: 14px;
            color: #FF0000;
        }
    </style>
</head>
<body>
<div id="wrapper">
    <jsp:include page="navtop.jsp"/>
    <!-- /. NAV TOP  -->

    <jsp:include page="navside.jsp"/>
    <!-- /. NAV SIDE  -->

    <div id="page-wrapper">
        <div id="page-inner">
            <div class="row">
                <div class="col-md-12">
                    <h2>Quản Lý Đơn Hàng</h2>
                    <h5>Chào mừng ${sessionScope.displayName}, rất vui được gặp lại bạn.</h5>
                </div>
            </div>
            <!-- /. ROW  -->
            <hr/>
            <div class="row d-flex justify-content-center">
                <div class="col-md-8">
                    <div class="panel panel-default">
                        <div class="panel-heading">Tạo đơn hàng</div>
                        <div class="panel-body">
                            <div class="row">
                                <%
                                    Locale vie = new Locale("vi", "VN");
                                    NumberFormat dongFormat = NumberFormat.getCurrencyInstance(vie);
                                %>
                                <div class="row ml-1 mr-1">
                                    <div class="col-md-6 mb-3"><label for="orderId" class="labels">Mã đơn hàng
                                        (auto)</label><input id="orderId" type="text" class="form-control"
                                                             name="orderId"
                                                             value="<% out.print(Constant.Service.ORDER_SERVICE.getNewestOrder().getOrderId() + 1); %>"
                                                             readonly></div>
                                    <div class="col-md-6 mb-3"><label for="username" class="labels">Người
                                        đặt</label><input id="username" type="text" class="form-control" name="username"
                                                          value="${sessionScope.orderAccount == null ? sessionScope.account.username : sessionScope.orderAccount}"
                                                          required></div>
                                    <div class="col-md-6 mb-3"><label for="recipientName" class="labels">Người
                                        nhận</label><input id="recipientName" type="text" class="form-control"
                                                           name="recipientName"
                                                           value="${sessionScope.ord_recipientName}"
                                                           placeholder="Tên người nhận" required></div>
                                    <div class="col-md-6 mb-3"><label for="recipientPhone" class="labels">SĐT người
                                        nhận</label><input id="recipientPhone" type="text" class="form-control"
                                                           name="recipientPhone" maxlength="12"
                                                           value="${sessionScope.ord_recipientPhone}"
                                                           placeholder="SĐT người nhận" required></div>
                                    <hr class="w-100 mt-3 mb-3">
                                    <div class="col-md-12"><label class="labels">Danh sách sản phẩm</label></div>
                                    <div class="col-md-12 mb-3 outer">
                                        <div class="panel-body">
                                            <div class="table-responsive">
                                                <table class="table table-striped table-bordered table-hover"
                                                       id="dataTables-example">
                                                    <thead>
                                                    <tr>
                                                        <th>STT</th>
                                                        <th>ID</th>
                                                        <th>Tên sản phẩm</th>
                                                        <th>Giá bán</th>
                                                        <th>Số lượng</th>
                                                        <th>Tổng</th>
                                                        <th>Tác vụ</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:set var="number" value="0"/>
                                                    <c:forEach items="${sessionScope.cartItems}" var="item">
                                                        <tr class="odd">
                                                            <td>${number = number + 1}</td>
                                                            <td>${item.product.productId}</td>
                                                            <td>${item.product.productName}</td>
                                                            <td>
                                                                <%
                                                                    CartItem item = (CartItem) pageContext.getAttribute("item");
                                                                    Product product = item.getProduct();
                                                                    out.print(dongFormat.format(product.getProductPrice()));
                                                                %>
                                                            </td>
                                                            <td>${item.quantity}</td>
                                                            <td><% out.print(dongFormat.format(item.getValue())); %></td>
                                                            <td>
                                                                <a href="<c:url value="/cart/remove?id=${item.cartItemId}&forwardTo=${pageContext.request.contextPath}/admin/order/add"/>"
                                                                   class="text-center">Xóa</a>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-12 mb-3 d-flex justify-content-center">
                                        <button id="btn-add-product" class="w-20 btn btn-primary ct-button"
                                                type="button"><i class="fa fa-plus"></i>&nbsp;Thêm sản phẩm
                                        </button>
                                    </div>
                                    <hr class="w-100 mt-3 mb-3">
                                    <form action="<c:url value="/shipping-cost"/>" method="get" class="col-md-12 mb-3">
                                        <label class="labels">Địa chỉ giao hàng</label>
                                        <div class="row">
                                            <!--Sign url-->
                                            <input type="hidden" name="forwardTo" value="/admin/order/add">
                                            <input type="hidden" name="signOrderAccount">
                                            <input type="hidden" name="signRecipientName">
                                            <input type="hidden" name="signRecipientPhone">

                                            <div class="col-md-6 mb-3">
                                                <label for="province" class="labels">Tỉnh/Thành phố</label>
                                                <input id="selectedProvince" type="hidden"
                                                       value="${sessionScope.selectedProvince}">
                                                <input type="hidden" name="selectedProvince">
                                                <select id="province" name="province" class="w-full form-control"
                                                        required>
                                                    <option value="0" selected hidden disabled>Tỉnh/Thành phố</option>
                                                </select>
                                            </div>

                                            <div class="col-md-6 mb-3">
                                                <label for="district" class="labels">Quận/Huyện</label>
                                                <input id="selectedDistrict" type="hidden"
                                                       value="${sessionScope.selectedDistrict}">
                                                <input type="hidden" name="selectedDistrict">
                                                <select id="district" name="district" class="w-full form-control"
                                                        required>
                                                    <option value="0" selected hidden disabled>Quận/Huyện</option>
                                                </select>
                                            </div>

                                            <div class="col-md-6 mb-3">
                                                <label for="ward" class="labels">Phường/Xã</label>
                                                <input id="selectedWard" type="hidden"
                                                       value="${sessionScope.selectedWard}">
                                                <input type="hidden" name="selectedWard">
                                                <select id="ward" name="ward" class="w-full form-control" required>
                                                    <option value="0" selected hidden disabled>Phường/Xã</option>
                                                </select>
                                            </div>

                                            <div class="col-md-6 mb-3">
                                                <label for="recaddress" class="labels">Số nhà</label>
                                                <input id="recaddress" name="recaddress" class="w-full form-control"
                                                       type="text" value="${sessionScope.recaddress}"
                                                       placeholder="Số nhà" required>
                                            </div>
                                        </div>
                                        <div class="col-md-12 mb-3 d-flex justify-content-center">
                                            <button id="btn-calculate-shipping" class="w-20 btn btn-primary ct-button"
                                                    type="submit"><i class="fa fa-calculator"></i>&nbsp;Tính phí ship
                                            </button>
                                        </div>
                                    </form>
                                    <hr class="w-100 mt-3 mb-3">
                                    <div class="col-md-12 mb-2">
                                        <label class="labels d-flex text-nowrap align-items-center float-right">Tổng
                                            tiền:&nbsp;<input type="text" class="form-control w-fit-content text-center"
                                                              value="<%
                                            List<CartItem> cartItems = Constant.Service.CART_ITEM_SERVICE.getItemByCart(((Cart) session.getAttribute("cart")).getCartId());
                                            BigDecimal subTotal = new BigDecimal(0);
                                            for (CartItem item : cartItems) {
                                                subTotal = subTotal.add(item.getValue());
                                            }
                                            out.print(dongFormat.format(subTotal));
                                        %>"></label>
                                    </div>
                                    <div class="col-md-12 mb-2">
                                        <label class="labels d-flex text-nowrap align-items-center float-right">Giảm
                                            giá:&nbsp;<input type="text" class="form-control w-fit-content text-center"
                                                             value="<% out.print(dongFormat.format(0)); %>"></label>
                                    </div>
                                    <div class="col-md-12 mb-2">
                                        <label class="labels d-flex text-nowrap align-items-center float-right">Thuế
                                            (8%):&nbsp;<input type="text" class="form-control w-fit-content text-center"
                                                              value="<%
                                            BigDecimal vat = subTotal.multiply(new BigDecimal(8)).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
                                            out.print(dongFormat.format(vat));
                                        %>"></label>
                                    </div>
                                    <div class="col-md-12 mb-2">
                                        <label class="labels d-flex text-nowrap align-items-center float-right">Phí vận
                                            chuyển:&nbsp;<input type="text"
                                                                class="form-control w-fit-content text-center" value="<%
                                            BigDecimal shippingCost = new BigDecimal(0);
                                            if (session.getAttribute("shippingCost") != null) {
                                                shippingCost = (BigDecimal) session.getAttribute("shippingCost");
                                            }
                                            out.print(dongFormat.format(shippingCost));
                                        %>"></label>
                                    </div>
                                    <div class="col-md-12 mb-2">
                                        <label class="labels d-flex text-nowrap align-items-center float-right"><strong>Thành
                                            tiền:</strong>&nbsp;<input type="text"
                                                                       class="form-control w-fit-content text-center product-price"
                                                                       value="<%
                                            BigDecimal total = (subTotal.add(shippingCost)).add(vat);
                                            out.print(dongFormat.format(total));
                                        %>"></label>
                                    </div>
                                </div>
                                <div class="row ml-1 mr-1">
                                    <div class="mt-5 text-center col-md-12 d-flex justify-content-end">
                                        <form action="<c:url value="/admin/order/add"/>" method="post">
                                            <input type="hidden" value="<% out.print(subTotal); %>" name="subTotal">
                                            <input type="hidden" value="0" name="discount">
                                            <input type="hidden" value="<% out.print(shippingCost); %>" name="shipping">
                                            <input type="hidden" value="<% out.print(vat); %>" name="vat">
                                            <input type="hidden" value="<% out.print(total); %>" name="total">
                                            <input type="hidden" name="fullName">
                                            <input type="hidden" name="phone">
                                            <input type="hidden" name="recaddress">
                                            <input type="hidden" name="selectedWard">
                                            <input type="hidden" name="selectedDistrict">
                                            <input type="hidden" name="selectedProvince">
                                            <input type="hidden" name="orderAccount">
                                            <input type="hidden" value="4" name="paymentMethod">

                                            <button id="btn-add-order" class="btn btn-primary ct-button" type="submit">
                                                <i class="fa fa-check"></i>&nbsp;Đồng ý
                                            </button>
                                        </form>
                                        <button class="btn btn-primary ct-button ml-3" type="reset"><i
                                                class="fa fa-undo-alt"></i>&nbsp;Nhập lại
                                        </button>
                                        <a href="${pageContext.request.contextPath}/admin/order"
                                           class="btn btn-primary ct-button ml-3"><i class="fa fa-angle-left"></i>&nbsp;Quay
                                            lại</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /. PAGE INNER  -->
    </div>
    <!-- /. PAGE WRAPPER  -->
</div>
<!-- /. WRAPPER  -->
<!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
<!-- JQUERY SCRIPTS -->
<script src="${pageContext.request.contextPath}/assets/vendor/jquery/jquery-3.2.1.min.js"></script>
<!-- BOOTSTRAP SCRIPTS -->
<script src="${pageContext.request.contextPath}/assets/vendor/bootstrap/js/bootstrap.min.js"></script>
<!-- METISMENU SCRIPTS -->
<script src="${pageContext.request.contextPath}/assets/js/jquery.metisMenu.js"></script>
<!-- CUSTOM SCRIPTS -->
<script src="${pageContext.request.contextPath}/assets/js/custom.js"></script>
<!--===============================================================================================-->
<!-- CK EDITOR -->
<script src="${pageContext.request.contextPath}/assets/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
    CKEDITOR.replace('brandDescription');
</script>
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/assets/js/axios.min.js"></script>
<!--===============================================================================================-->
<script>
    $(function () {
        const provinces = document.getElementById("province");
        const districts = document.getElementById("district");
        const wards = document.getElementById("ward");

        const Parameter = {
            url: '${pageContext.request.contextPath}/assets/js/vietnam.json',
            method: 'GET',
            responseType: 'application/json'
        };

        const promise = axios(Parameter);
        promise.then(function (result) {
            renderProvince(result.data);
        });

        function renderProvince(data) {
            for (const x of data) {
                provinces.options[provinces.options.length] = new Option(x.Name, x.Id);
            }

            provinces.onchange = function () {
                districts.length = 1;
                wards.length = 1;
                if (this.value !== "") {
                    const result = data.filter(n => n.Id === this.value);

                    for (const k of result[0].Districts) {
                        districts.options[districts.options.length] = new Option(k.Name, k.Id);
                    }
                }
            };

            districts.onchange = function () {
                wards.length = 1;
                const dataProvince = data.filter((n) => n.Id === provinces.value);
                if (this.value !== "") {
                    const dataWards = dataProvince[0].Districts.filter(n => n.Id === this.value)[0].Wards;

                    for (const w of dataWards) {
                        wards.options[wards.options.length] = new Option(w.Name, w.Id);
                    }
                }
            };

            const selectedProvince = $('#selectedProvince').prop('value');
            const selectedDistrict = $('#selectedDistrict').prop('value');
            const selectedWard = $('#selectedWard').prop('value');
            if (selectedProvince !== '') {
                $('#province').val(selectedProvince).trigger('change');
            }
            if (selectedDistrict !== '') {
                $('#district').val(selectedDistrict).trigger('change');
            }
            if (selectedWard !== '') {
                $('#ward').val(selectedWard);
            }
        }
    });
</script>
<!--===============================================================================================-->
<script>
    $(function () {
        $('#btn-add-product').on('click', function () {
            const component =
                "<form action=\"<c:url value="/cart/add"/>\" method=\"get\">"
                + "\n" +
                "<input type=\"hidden\" name=\"forwardTo\" value=\"${pageContext.request.contextPath}/admin/order/add\">"
                + "\n" +
                "<input class=\"ip-username\" type=\"hidden\" name=\"username\">"
                + "\n" +
                "<input class=\"ip-recipientName\" type=\"hidden\" name=\"recipientName\">"
                + "\n" +
                "<input class=\"ip-recipientPhone\" type=\"hidden\" name=\"recipientPhone\">"
                + "\n\n" +
                "<div class=\"d-flex mb-3\">"
                + "\n" +
                "<label class=\"labels d-flex align-items-center mb-0 wsp-nowrap\">Mã sản phẩm:</label>&nbsp;<input type=\"text\" class=\"form-control w-20 mr-4\" name=\"id\">"
                + "\n" +
                "<label class=\"labels d-flex align-items-center mb-0 wsp-nowrap\">Số lượng:</label>&nbsp;<input type=\"number\" class=\"form-control w-20 mr-5\" min=\"1\" value=\"1\" name=\"num-product\">"
                + "\n" +
                "<button class=\"w-10 btn btn-primary ct-button mr-3 btn-add\" type=\"button\">Thêm</button>"
                + "\n" +
                "</div>"
                + "\n" +
                "</form>"
            $('.outer').append(component);
            $('.btn-add').each(function () {
                $(this).on('click', function () {
                    //username
                    $(this).parent().prev().prev().prev().val($('#username').prop('value'));
                    //recipientName
                    $(this).parent().prev().prev().val($('#recipientName').prop('value'));
                    //recipientPhone
                    $(this).parent().prev().val($('#recipientPhone').prop('value'));
                    //form
                    $(this).parent().parent().submit();
                });
            });
        });
    });
</script>
<!--===============================================================================================-->
<script>
    $(function () {
        $('#btn-add-order').on('click', function () {
            $('input[name="fullName"]').val($('#recipientName').prop('value'));
            $('input[name="phone"]').val($('#recipientPhone').prop('value'));
            //recaddress
            $(this).prev().prev().prev().prev().prev().prev().val($('#recaddress').prop('value'));
            //selectedWard
            $(this).prev().prev().prev().prev().prev().val($('#ward option:selected').text());
            //selectedDistrict
            $(this).prev().prev().prev().prev().val($('#district option:selected').text());
            //selectedProvince
            $(this).prev().prev().prev().val($('#province option:selected').text());
            //orderAccount
            $(this).prev().prev().val($('#username').prop('value'));
        });
    });
</script>
<!--===============================================================================================-->
<script>
    $(function () {
        $('#btn-calculate-shipping').on('click', function () {
            $('input[name="signOrderAccount"]').val($('#username'));
            $('input[name="signRecipientName"]').val($('#recipientName'));
            $('input[name="signRecipientPhone"]').val($('#recipientPhone'));
        });
    });
</script>

</body>
</html>
