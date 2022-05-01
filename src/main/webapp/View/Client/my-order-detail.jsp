<jsp:useBean id="myOrder" scope="request" type="Entity.Order"/>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="Entity.Order" %>
<%@ page import="Util.Constant" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="Entity.CartItem" %>
<%--
  User: duckhaidev
  Date: 4/28/2022
  Time: 8:45 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Theo Dõi Đơn Hàng - Đồ gỗ Cao cấp Đông Phong</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/images/icons/icon-logo.png"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/fonts/fontawesome-pro-5.15.4-web/css/all.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/fonts/iconic/css/material-design-iconic-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/fonts/linearicons-v1.0.0/icon-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/vendor/animsition/css/animsition.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/vendor/perfect-scrollbar/perfect-scrollbar.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/util.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/main.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/custom.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/assets/css/my-account-stylesheet.css">
    <!-- TABLE STYLES-->
    <link href="${pageContext.request.contextPath}/assets/js/dataTables-bs4/dataTables.bootstrap4.min.css"
          rel="stylesheet"/>
</head>
<body class="animsition">

<!-- Header -->
<jsp:include page="header-v4.jsp"/>

<!-- Cart -->
<jsp:include page="cart.jsp"/>

<!-- WishList -->
<jsp:include page="wishList.jsp"/>

<!-- Content page -->
<div class="container rounded bg-white mt-5 mb-5">
    <div class="row justify-content p-4">
        <div class="col-md-12">
            <h5 class="mb-3 text-start">Đơn hàng #${myOrder.orderId}</h5>
            <div class="bor21 p-3">
                <div class="row d-flex mb-3">
                    <div class="col-md-12"><span class="float-right">
                    <%
                        Order order = (Order) request.getAttribute("myOrder");
                        if (order != null) {
                            Timestamp timestamp = order.getOrderDate();
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
                        <div class="col-md-6 mb-3"><label for="fullName" class="labels">Họ và tên</label><input
                                id="fullName"
                                name="fullName"
                                type="text"
                                class="form-control"
                                value="${myOrder.recipientName}"
                                readonly></div>
                        <div class="col-md-6 mb-3"><label for="phone" class="labels">Số điện thoại</label><input
                                id="phone"
                                name="phone"
                                type="text"
                                class="form-control"
                                value="${myOrder.recipientPhone}"
                                readonly></div>
                        <div class="col-md-12 mb-3"><label for="recaddress" class="labels">Địa chỉ</label><input
                                type="text"
                                id="recaddress"
                                class="form-control"
                                value="${myOrder.recipientAddress}"
                                readonly></div>
                    </div>
                </div>
                <div class="bor20 p-3 mb-3">
                    <div class="mb-3 text-center text-uppercase">Phương thức vận chuyển</div>
                    <div class="row d-flex mb-2 align-items-center">
                        <div class="col-md-2"><label for="ip1" class="labels text-nowrap">Đơn vị vận
                            chuyển:&nbsp;</label></div>
                        <div class="col-md-4"><input id="ip1" type="text" class="form-control text-center"
                                                     value="Đông Phong" readonly></div>
                    </div>
                    <div class="row d-flex mb-3 align-items-center">
                        <div class="col-md-2"><label for="ip2" class="labels text-nowrap">Thời gian giao hàng dự kiến:&nbsp;</label>
                        </div>
                        <div class="col-md-4"><input id="ip2" type="text" class="form-control text-center"
                                                     value="${myOrder.recipientDate} <c:if test="${myOrder.recipientDate == null}">5 - 7 ngày làm việc (Trừ ngày nghỉ và lễ tết)</c:if>"
                                                     readonly></div>
                    </div>
                </div>
                <div class="bor20 p-3 mb-3">
                    <div class="mb-3 text-center text-uppercase">Chi tiết đơn hàng</div>
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
                                                String reImage = Constant.Service.PRO_IMAGE_SERVICE.getProReImage(((CartItem) pageContext.getAttribute("item")).getProduct().getProductId());
                                                request.setAttribute("reImage", reImage);
                                            %>
                                            <c:url value="/images/product-images?fname=${reImage}" var="imageUrl"/>
                                            <c:choose>
                                                <c:when test="${reImage == null}">
                                                    <td><i>NULL</i></td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td><img width="50" height="50" src="${imageUrl}"
                                                             style="object-fit: cover;"
                                                             alt="Hình ảnh"></td>
                                                </c:otherwise>
                                            </c:choose>
                                            <td>${item.product.productName}</td>
                                            <td>
                                                <%
                                                    BigDecimal price = ((CartItem) pageContext.getAttribute("item")).getProduct().getProductPrice();
                                                    out.print(Constant.NF_DONG.format(price));
                                                %>
                                            </td>
                                            <td>${item.quantity}</td>
                                            <td>
                                                <%
                                                    BigDecimal value = ((CartItem) pageContext.getAttribute("item")).getValue();
                                                    out.print(Constant.NF_DONG.format(value));
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
                            <label class="labels d-flex text-nowrap align-items-center float-right">Tổng
                                tiền:&nbsp;<input
                                        type="text" class="form-control w-fit-content text-center"
                                        value="<% out.print(Constant.NF_DONG.format(subTotal)); %>" readonly></label>
                        </div>
                        <div class="col-md-12 mb-2">
                            <label class="labels d-flex text-nowrap align-items-center float-right">Giảm
                                giá:&nbsp;<input
                                        type="text" class="form-control w-fit-content text-center"
                                        value="<% out.print(Constant.NF_DONG.format(discount)); %>" readonly></label>
                        </div>
                        <div class="col-md-12 mb-2">
                            <label class="labels d-flex text-nowrap align-items-center float-right">Thuế
                                (8%):&nbsp;<input
                                        type="text" class="form-control w-fit-content text-center"
                                        value="<% out.print(Constant.NF_DONG.format(tax)); %>" readonly></label>
                        </div>
                        <div class="col-md-12 mb-2">
                            <label class="labels d-flex text-nowrap align-items-center float-right">Phí vận chuyển:&nbsp;<input
                                    type="text" class="form-control w-fit-content text-center"
                                    value="<% out.print(Constant.NF_DONG.format(shipping)); %>" readonly></label>
                        </div>
                        <div class="col-md-12 mb-2">
                            <label class="labels d-flex text-nowrap align-items-center float-right"><strong>Thành
                                tiền:</strong>&nbsp;<input
                                    type="text" class="form-control w-fit-content text-center product-price"
                                    value="<% out.print(Constant.NF_DONG.format(total)); %>" readonly></label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-12 mt-4 text-end col-md-6">
                <a href="${pageContext.request.contextPath}/member/my-order" class="btn btn-primary profile-button"><i
                        class="fa fa-angle-left"></i>&nbsp;Quay
                    lại
                </a>
            </div>
        </div>
    </div>
</div>

<!-- Footer -->
<jsp:include page="footer.jsp"/>

<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/assets/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/assets/vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/assets/vendor/bootstrap/js/popper.js"></script>
<script src="${pageContext.request.contextPath}/assets/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/assets/vendor/select2/select2.min.js"></script>
<script>
    $(".js-select2").each(function () {
        $(this).select2({
            minimumResultsForSearch: 20,
            dropdownParent: $(this).next('.dropDownSelect2')
        });
    })
</script>
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/assets/vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/assets/vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
<script>
    $('.js-pscroll').each(function () {
        $(this).css('position', 'relative');
        $(this).css('overflow', 'hidden');
        const ps = new PerfectScrollbar(this, {
            wheelSpeed: 1,
            scrollingThreshold: 1000,
            wheelPropagation: false,
        });

        $(window).on('resize', function () {
            ps.update();
        })
    });
</script>
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/assets/js/custom.js"></script>
<!-- DATA TABLE SCRIPTS -->
<script src="${pageContext.request.contextPath}/assets/js/dataTables-bs4/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/dataTables-bs4/dataTables.bootstrap4.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/dataTables-bs4/dataTables.responsive.min.js"></script>
<script>
    $(document).ready(function () {
        $('#dataTable').dataTable({
            "pagingType": "full_numbers"
        });
    });
</script>

</body>
</html>
