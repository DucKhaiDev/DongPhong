<%@ page import="java.math.BigDecimal" %>
<%@ page import="Entity.Order" %>
<%@ page import="Util.Constant" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.ZoneId" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  User: duckhaidev
  Date: 4/28/2022
  Time: 5:55 PM
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
            <h4 class="text-left">Đơn hàng của bạn</h4>
            <hr>
            <div class="table-responsive mt-3">
                <table class="table table-striped table-bordered table-hover" id="dataTable">
                    <thead>
                    <tr>
                        <th style="width: 5%;">STT</th>
                        <th style="width: 5%;">ID</th>
                        <th>Người đặt</th>
                        <th>Người nhận</th>
                        <th style="width: 20%;">Địa chỉ giao hàng</th>
                        <th>SĐT người nhận</th>
                        <th>Tổng tiền</th>
                        <th>Tình trạng</th>
                        <th>Ngày nhận</th>
                        <th>Tác vụ</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="number" value="0"/>
                    <jsp:useBean id="myOrders" scope="request" type="java.util.List"/>
                    <c:forEach items="${myOrders}" var="order">
                        <tr class="odd">
                            <td>${number = number + 1}</td>
                            <td>${order.orderId}</td>
                            <td>${order.user.username}</td>
                            <td>${order.recipientName}</td>
                            <td>${order.recipientAddress}</td>
                            <td>${order.recipientPhone}</td>
                            <td>
                                <%
                                    Order order = (Order) pageContext.getAttribute("order");
                                    BigDecimal total = order.getOrderTotal();
                                    out.print(Constant.NF_DONG.format(total));
                                %>
                            </td>
                            <td>
                                <%
                                    byte status = order.getOrderStatus();
                                    if (status == 1) {
                                        out.print("Đang xử lý");
                                    } else if (status == 2) {
                                        out.print("Đang vận chuyển");
                                    } else if (status == 3) {
                                        out.print("Đã giao");
                                    } else {
                                        out.print("Đã hủy");
                                    }
                                %>
                            </td>
                            <td>
                                <%
                                    if (order.getRecipientDate() != null) {
                                        out.println(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(order.getRecipientDate()));
                                    } else {
                                        out.println("Chưa xác định");
                                    }
                                %>
                            </td>
                            <td>
                                <a href="<c:url value="/member/my-order/detail?id=${order.orderId}"/>"
                                   class="text-center">Chi tiết</a>&nbsp;|&nbsp;
                                <c:choose>
                                    <c:when test="${order.orderStatus == 1}">
                                        <a href="<c:url value="/member/my-order/cancel?id=${order.orderId}"/>"
                                           class="text-center">Hủy</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="text-center isDisabled pointer">Hủy</a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
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
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/assets/vendor/sweetalert/sweetalert.min.js"></script>
<script>
    $(function () {
        const message = '${sessionScope.overtime}';
        if (message !== '') {
            swal({
                text: '${sessionScope.overtime}',
                icon: 'warning'
            }).then(function () {
                <% session.removeAttribute("overtime"); %>
            });
        }
    });
</script>

</body>
</html>
