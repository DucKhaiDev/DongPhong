<%@ page import="java.math.BigDecimal" %>
<%@ page import="Entity.Order" %>
<%@ page import="Util.Constant" %>
<%--
  User: duckhaidev
  Date: 4/8/2022
  Time: 12:08 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Quản Lý Đơn Đặt Hàng - Đồ gỗ Cao cấp Đông Phong</title>
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/images/icons/icon-logo.png"/>
    <!-- BOOTSTRAP STYLES-->
    <link href="${pageContext.request.contextPath}/assets/css/bootstrap.css" rel="stylesheet"/>
    <!-- FONTAWESOME STYLES-->
    <link href="${pageContext.request.contextPath}/assets/fonts/fontawesome-pro-5.15.4-web/css/all.min.css"
          rel="stylesheet"/>
    <!-- MORRIS CHART STYLES-->

    <!-- CUSTOM STYLES-->
    <link href="${pageContext.request.contextPath}/assets/css/custom.css" rel="stylesheet"/>
    <!-- GOOGLE FONTS-->
    <link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'/>
    <!-- TABLE STYLES-->
    <link href="${pageContext.request.contextPath}/assets/js/dataTables/dataTables.bootstrap.css" rel="stylesheet"/>
    <%---------------------------------------------------------------------------------------------%>
    <style>
        .table > tbody > tr > td > p {
            font-size: 14px;
            line-height: 20px;
            padding-top: 0;
            margin: 0;
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
                    <div class="row">
                        <div class="col-md-12"><h5>Chào mừng ${sessionScope.displayName}, rất vui được gặp lại
                            bạn. </h5></div>
                    </div>

                </div>
            </div>
            <!-- /. ROW  -->
            <hr/>
            <div class="row">
                <div class="col-md-12 mb-3">
                    <a href="${pageContext.request.contextPath}/admin/order/add"
                       class="btn btn-primary ct-button float-right">
                        <i class="fa fa-plus"></i>&nbsp;Tạo đơn hàng
                    </a>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <!-- Advanced Tables -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Danh sách đơn hàng
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>ID</th>
                                        <th>Người đặt</th>
                                        <th>Người nhận</th>
                                        <th style="width: 20%">Địa chỉ giao hàng</th>
                                        <th>SĐT người nhận</th>
                                        <th>Tổng tiền</th>
                                        <th>Tình trạng</th>
                                        <th>Tác vụ</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:set var="number" value="0"/>
                                    <jsp:useBean id="orders" scope="request" type="java.util.List"/>
                                    <c:forEach items="${orders}" var="order">
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
                                                    BigDecimal total = (order).getOrderTotal();
                                                    out.print(Constant.NF_DONG.format(total));
                                                %>
                                            </td>
                                            <td>
                                                <%
                                                    byte status = order.getOrderStatus();
                                                    if (status == 1) {
                                                        out.print("Chưa xử lý");
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
                                                <a href="<c:url value="/admin/order/detail?id=${order.orderId}"/>"
                                                   class="text-center">Chi tiết</a>&nbsp;|&nbsp;
                                                <a href="<c:url value="/admin/order/edit?id=${order.orderId}"/>"
                                                   class="text-center">Sửa</a>&nbsp;|&nbsp;
                                                <a href="<c:url value="/admin/order/delete?id=${order.orderId}"/>"
                                                   class="text-center">Xóa</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>

                        </div>
                    </div>
                    <!--End Advanced Tables -->
                </div>
            </div>
            <!-- /. ROW  -->
        </div>

    </div>
    <!-- /. PAGE INNER  -->
</div>
<!-- /. PAGE WRAPPER  -->
<!-- /. WRAPPER  -->
<!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
<!-- JQUERY SCRIPTS -->
<script src="${pageContext.request.contextPath}/assets/vendor/jquery/jquery-3.2.1.min.js"></script>
<!-- BOOTSTRAP SCRIPTS -->
<script src="${pageContext.request.contextPath}/assets/vendor/bootstrap/js/bootstrap.min.js"></script>
<!-- METISMENU SCRIPTS -->
<script src="${pageContext.request.contextPath}/assets/js/jquery.metisMenu.js"></script>
<!-- DATA TABLE SCRIPTS -->
<script src="${pageContext.request.contextPath}/assets/js/dataTables/jquery.dataTables.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/dataTables/dataTables.bootstrap.js"></script>
<script>
    $(document).ready(function () {
        $('#dataTables-example').dataTable({
            "pagingType": "full_numbers"
        });
    });
</script>
<!-- CUSTOM SCRIPTS -->
<script src="${pageContext.request.contextPath}/assets/js/custom.js"></script>

</body>
</html>
