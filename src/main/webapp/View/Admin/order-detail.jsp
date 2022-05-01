<%@ page import="Entity.Order" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="Entity.CartItem" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="Util.Constant" %>
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
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Chi Tiết Đơn Hàng ${order.orderId} - Đồ gỗ Cao cấp Đông Phong</title>
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/images/icons/icon-logo.png"/>
    <!-- BOOTSTRAP STYLES-->
    <link href="${pageContext.request.contextPath}/assets/css/bootstrap.css" rel="stylesheet"/>
    <!-- FONTAWESOME STYLES-->
    <link href="${pageContext.request.contextPath}/assets/fonts/fontawesome-pro-5.15.4-web/css/all.min.css"
          rel="stylesheet"/>
    <!-- CUSTOM STYLES-->
    <link href="${pageContext.request.contextPath}/assets/css/custom.css" rel="stylesheet"/>
    <!-- GOOGLE FONTS-->
    <link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'/>
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
            <div class="row d-flex flex-column justify-content-spa align-items-center">
                <div class="col-md-8 mb-3">
                    <a href="${pageContext.request.contextPath}/admin/order"
                       class="btn btn-primary float-right ct-button">
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
                                <div class="col-md-6 mb-3"><label for="fullName" class="labels">Họ và tên</label><input
                                        id="fullName" name="fullName" type="text" class="form-control"
                                        value="${order.recipientName}" readonly></div>
                                <div class="col-md-6 mb-3"><label for="phone" class="labels">Số điện thoại</label><input
                                        id="phone" name="phone" type="text" class="form-control"
                                        value="${order.recipientPhone}" readonly></div>
                                <div class="col-md-12 mb-3"><label for="recaddress" class="labels">Địa chỉ</label><input
                                        type="text" id="recaddress" class="form-control"
                                        value="${order.recipientAddress}" readonly></div>
                            </div>
                        </div>
                        <div class="bor20 p-3 mb-3">
                            <div class="mb-3 text-center text-uppercase">Phương thức vận chuyển</div>
                            <div class="row d-flex mb-2 align-items-center">
                                <div class="col-md-2"><label for="ip1" class="labels text-nowrap">Đơn vị vận chuyển:&nbsp;</label></div>
                                <div class="col-md-6"><input id="ip1" type="text" class="form-control text-center"
                                                             value="Đông Phong" readonly></div>
                            </div>
                            <div class="row d-flex mb-3 align-items-center">
                                <c:choose>
                                    <c:when test="${order.recipientDate == null}">
                                        <div class="col-md-2"><label for="ip2" class="labels text-nowrap">Thời gian giao hàng dự kiến:&nbsp;</label>
                                        </div>
                                        <div class="col-md-6"><input id="ip2" type="text" class="form-control text-center"
                                                                     value="5 - 7 ngày làm việc (Trừ ngày nghỉ và lễ tết)"
                                                                     readonly></div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="col-md-2"><label for="ip3" class="labels text-nowrap">Giao hàng thành công:&nbsp;</label>
                                        </div>
                                        <div class="col-md-6"><input id="ip3" type="text" class="form-control text-center"
                                                                     value="<%
                                                                        SimpleDateFormat formatDow = new SimpleDateFormat("EEEE", Constant.LC_VIETNAM);
                                                                        SimpleDateFormat formatDam = new SimpleDateFormat("dd MMMM", Constant.LC_VIETNAM);
                                                                        SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");
                                                                        assert order != null;
                                                                        java.sql.Date recDate = order.getRecipientDate();
                                                                        out.print(formatDow.format(recDate) + ", ngày " + formatDam.format(recDate) + " năm " + formatYear.format(recDate));
                                                                     %>"
                                                                     readonly></div>
                                    </c:otherwise>
                                </c:choose>
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
                                                    <c:url value="/images/product-images?fname=${reImage}"
                                                           var="imageUrl"/>
                                                    <c:choose>
                                                        <c:when test="${reImage == null}">
                                                            <td><i>NULL</i></td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td><img width="50" height="50" src="${imageUrl}"
                                                                     style="object-fit: cover;" alt="Hình ảnh"></td>
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
                                    <label class="labels d-flex text-nowrap align-items-center float-right">Tổng tiền:&nbsp;<input
                                            type="text" class="form-control w-fit-content text-center"
                                            value="<% out.print(Constant.NF_DONG.format(subTotal)); %>" readonly></label>
                                </div>
                                <div class="col-md-12 mb-2">
                                    <label class="labels d-flex text-nowrap align-items-center float-right">Giảm giá:&nbsp;<input
                                            type="text" class="form-control w-fit-content text-center"
                                            value="<% out.print(Constant.NF_DONG.format(discount)); %>" readonly></label>
                                </div>
                                <div class="col-md-12 mb-2">
                                    <label class="labels d-flex text-nowrap align-items-center float-right">Thuế (8%):&nbsp;<input
                                            type="text" class="form-control w-fit-content text-center"
                                            value="<% out.print(Constant.NF_DONG.format(tax)); %>" readonly></label>
                                </div>
                                <div class="col-md-12 mb-2">
                                    <label class="labels d-flex text-nowrap align-items-center float-right">Phí vận
                                        chuyển:&nbsp;<input type="text" class="form-control w-fit-content text-center"
                                                            value="<% out.print(Constant.NF_DONG.format(shipping)); %>"
                                                            readonly></label>
                                </div>
                                <div class="col-md-12 mb-2">
                                    <label class="labels d-flex text-nowrap align-items-center float-right"><strong>Thành
                                        tiền:</strong>&nbsp;<input type="text"
                                                                   class="form-control w-fit-content text-center product-price"
                                                                   value="<% out.print(Constant.NF_DONG.format(total)); %>"
                                                                   readonly></label>
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

</body>
</html>

