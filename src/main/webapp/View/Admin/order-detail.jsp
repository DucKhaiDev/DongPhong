<%@ page import="Entity.Order" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="Services.deploy.ProImageService" %>
<%@ page import="Entity.CartItem" %>
<%@ page import="java.math.BigDecimal" %>
<jsp:useBean id="order" scope="request" type="Entity.Order"/>
<%--
  User: duckhaidev
  Date: 4/13/2022
  Time: 9:49 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Dong Phong</title>
    <!-- BOOTSTRAP STYLES-->
    <link href="${pageContext.request.contextPath}/assets/css/bootstrap.css" rel="stylesheet"/>
    <!-- FONTAWESOME STYLES-->
    <link href="${pageContext.request.contextPath}/assets/fonts/fontawesome-pro-5.15.4-web/css/all.min.css" rel="stylesheet" />
    <!-- CUSTOM STYLES-->
    <link href="${pageContext.request.contextPath}/assets/css/custom.css" rel="stylesheet" />
    <!-- GOOGLE FONTS-->
    <link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
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

    <div id="page-wrapper" >
        <div id="page-inner">
            <div class="row">
                <div class="col-md-12">
                    <h2>Quản Lý Đơn Hàng</h2>
                    <h5>Chào mừng ${sessionScope.displayName}, rất vui được gặp lại bạn.</h5>
                </div>
            </div>
            <!-- /. ROW  -->
            <hr />
            <div class="row d-flex flex-column justify-content-spa align-items-center">
                <div class="col-md-8 mb-3">
                    <a href="${pageContext.request.contextPath}/admin/order" class="btn btn-primary float-right ct-button">
                        <i class="fa fa-angle-left"></i>&nbsp;Quay lại
                    </a>
                </div>
                <div class="col-md-8">
                    <h5 class="mb-3 text-start">Đơn hàng #${order.orderId}</h5>
                    <div class="bor21 p-3">
                        <div class="row d-flex mb-3">
                            <div class="col-md-12"><span class="float-right">
                                <%
                                    if (order != null) {
                                        Timestamp timestamp = ((Order) request.getAttribute("order")).getOrderDate();
                                        Date date = new Date();
                                        date.setTime(timestamp.getTime());
                                        out.print(new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(date));
                                    }
                                %>
                            </span></div>
                        </div>
                        <div class="bor20 p-3 mb-3">
                            <div class="mb-3 text-center text-uppercase">Thông tin giao hàng</div>
                            <div class="row d-flex justify-content-center">
                                <div class="col-md-6 mb-3"><label for="fullName" class="labels">Họ và tên</label><input id="fullName" name="fullName" type="text" class="form-control" value="${order.recipientName}" readonly></div>
                                <div class="col-md-6 mb-3"><label for="phone" class="labels">Số điện thoại</label><input id="phone" name="phone" type="text" class="form-control" value="${order.recipientPhone}" readonly></div>
                                <div class="col-md-12 mb-3"><label for="recaddress" class="labels">Địa chỉ</label><input type="text" id="recaddress" class="form-control" value="${order.recipientAddress}" readonly></div>
                            </div>
                        </div>
                        <div class="bor20 p-3 mb-3">
                            <div class="mb-3 text-center text-uppercase">Phương thức vận chuyển</div>
                            <div class="row d-flex mb-2 align-items-center">
                                <div class="col-md-2"><label for="ip1" class="labels text-nowrap">Đơn vị vận chuyển:&nbsp;</label></div>
                                <div class="col-md-2"><input id="ip1" type="text" class="form-control w-fit-content text-center" value="Đông Phong" readonly></div>
                            </div>
                            <div class="row d-flex mb-3 align-items-center">
                                <div class="col-md-2"><label for="ip2" class="labels text-nowrap">Thời gian giao hàng dự kiến:&nbsp;</label></div>
                                <div class="col-md-2"><input id="ip2" type="text" class="form-control w-fit-content text-center" value="${order.recipientDate} <c:if test="${order.recipientDate == null}">Chưa xác định</c:if>" readonly></div>
                            </div>
                        </div>
                        <div class="bor20 p-3 mb-3">
                            <div class="mb-3 text-center text-uppercase">Chi tiết đơn hàng</div>
                            <%
                                Locale vie = new Locale("vi", "VN");
                                NumberFormat dongFormat = NumberFormat.getCurrencyInstance(vie);
                            %>
                            <div class="row">
                                <div class="col-md-12 mb-5">
                                    <div class="table-responsive">
                                        <table class="table table-striped table-bordered table-hover" id="dataTable">
                                            <thead>
                                            <tr>
                                                <th>STT</th>
                                                <th>Mã sản phẩm</th>
                                                <th>Hình ảnh</th>
                                                <th>Tên sản phẩm</th>
                                                <th>Giá tiền</th>
                                                <th>Số lượng</th>
                                                <th>Tổng tiền</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:set var="count" value="0"/>
                                            <jsp:useBean id="cartItems" scope="request" type="java.util.List"/>
                                            <c:forEach items="${cartItems}" var="item">
                                                <tr class="odd">
                                                    <td>${count = count + 1}</td>
                                                    <td>${item.product.productId}</td>
                                                    <%
                                                        ProImageService imageService = new ProImageService();
                                                        String reImage = imageService.getProReImage(((CartItem) pageContext.getAttribute("item")).getProduct().getProductId());
                                                        request.setAttribute("reImage", reImage);
                                                    %>
                                                    <c:url value="/images/product-images?fname=${reImage}" var="imageUrl"/>
                                                    <c:choose>
                                                        <c:when test="${reImage == null}">
                                                            <td><i>NULL</i></td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td><img width="50" height="50" src="${imageUrl}" style="object-fit: cover;" alt="Hình ảnh"></td>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <td>${item.product.productName}</td>
                                                    <td>
                                                        <%
                                                            BigDecimal price = ((CartItem) pageContext.getAttribute("item")).getProduct().getProductPrice();
                                                            out.print(dongFormat.format(price));
                                                        %>
                                                    </td>
                                                    <td>${item.quantity}</td>
                                                    <td>
                                                        <%
                                                            BigDecimal value = ((CartItem) pageContext.getAttribute("item")).getValue();
                                                            out.print(dongFormat.format(value));
                                                        %>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <%
                                    BigDecimal subTotal = new BigDecimal(0);
                                    BigDecimal discount = new BigDecimal(0);
                                    BigDecimal tax = new BigDecimal(0);
                                    BigDecimal shipping = new BigDecimal(0);
                                    BigDecimal total = new BigDecimal(0);

                                    if (order != null) {
                                        subTotal = order.getOrderSubTotal();
                                        discount = order.getOrderDiscount();
                                        tax = order.getOrderTax();
                                        shipping = order.getOrderShipping();
                                        total = order.getOrderTotal();
                                    }
                                %>
                                <div class="col-md-12 mb-2">
                                    <label class="labels d-flex text-nowrap align-items-center float-right">Tổng tiền:&nbsp;<input type="text" class="form-control w-fit-content text-center" value="<% out.print(dongFormat.format(subTotal)); %>" readonly></label>
                                </div>
                                <div class="col-md-12 mb-2">
                                    <label class="labels d-flex text-nowrap align-items-center float-right">Giảm giá:&nbsp;<input type="text" class="form-control w-fit-content text-center" value="<% out.print(dongFormat.format(discount)); %>" readonly></label>
                                </div>
                                <div class="col-md-12 mb-2">
                                    <label class="labels d-flex text-nowrap align-items-center float-right">Thuế (8%):&nbsp;<input type="text" class="form-control w-fit-content text-center" value="<% out.print(dongFormat.format(tax)); %>" readonly></label>
                                </div>
                                <div class="col-md-12 mb-2">
                                    <label class="labels d-flex text-nowrap align-items-center float-right">Phí vận chuyển:&nbsp;<input type="text" class="form-control w-fit-content text-center" value="<% out.print(dongFormat.format(shipping)); %>" readonly></label>
                                </div>
                                <div class="col-md-12 mb-2">
                                    <label class="labels d-flex text-nowrap align-items-center float-right"><strong>Thành tiền:</strong>&nbsp;<input type="text" class="form-control w-fit-content text-center product-price" value="<% out.print(dongFormat.format(total)); %>" readonly></label>
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

