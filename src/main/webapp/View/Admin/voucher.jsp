<%@ page import="Entity.Voucher" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="Util.Constant" %>
<%--
  User: duckhaidev
  Date: 4/15/2022
  Time: 3:14 PM
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
                    <h2>Quản Lý Mã Giảm Giá</h2>
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
                    <a href="${pageContext.request.contextPath}/admin/voucher/add"
                       class="btn btn-primary ct-button float-right">
                        <i class="fa fa-plus"></i>&nbsp;Tạo mã
                    </a>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <!-- Advanced Tables -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Danh sách Mã giảm giá
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>Mã giảm giá</th>
                                        <th>Số Sản phẩm MIN</th>
                                        <th>Giá trị giỏ hàng MIN</th>
                                        <th>Giảm</th>
                                        <th>HSD từ ngày</th>
                                        <th>HSD đến ngày</th>
                                        <th>Tác vụ</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:set var="number" value="0"/>
                                    <jsp:useBean id="vouchers" scope="request" type="java.util.List"/>
                                    <c:forEach items="${vouchers}" var="voucher">
                                        <tr class="odd">
                                            <td>${number = number + 1}</td>
                                            <td>${voucher.voucherId}</td>
                                            <td>${voucher.minProduct}</td>
                                            <%
                                                Voucher voucher = (Voucher) pageContext.getAttribute("voucher");
                                            %>
                                            <td><% out.print(Constant.NF_DONG.format(voucher.getMinValue())); %></td>
                                            <td><% out.print(String.format("%.0f", voucher.getDiscount() * 100) + " %"); %></td>
                                            <%
                                                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                                Date fromDate = new Date();
                                                fromDate.setTime(voucher.getFromDate().getTime());
                                                Date toDate = new Date();
                                                toDate.setTime(voucher.getToDate().getTime());
                                            %>
                                            <td><% out.print(dateFormat.format(fromDate)); %></td>
                                            <td><% out.print(dateFormat.format(toDate)); %></td>
                                            <td>
                                                <a href="<c:url value="/admin/voucher/edit?id=${voucher.voucherId}"/>"
                                                   class="text-center">Sửa</a>&nbsp;|&nbsp;
                                                <a href="<c:url value="/admin/voucher/delete?id=${voucher.voucherId}"/>"
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
