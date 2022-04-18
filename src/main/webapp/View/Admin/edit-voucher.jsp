<jsp:useBean id="voucher" scope="request" type="Entity.Voucher"/>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="Entity.Voucher" %>
<%--
  User: duckhaidev
  Date: 4/15/2022
  Time: 11:28 PM
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
                    <h2>Quản Lý Mã Giảm Giá</h2>
                    <h5>Chào mừng ${sessionScope.displayName}, rất vui được gặp lại bạn. </h5>
                </div>
            </div>
            <!-- /. ROW  -->
            <hr />
            <div class="row d-flex justify-content-center">
                <div class="col-md-8">
                    <div class="panel panel-default">
                        <div class="panel-heading">Cập nhật Mã giảm giá</div>
                        <div class="panel-body">
                            <div class="row">
                                <form action="<c:url value="/admin/voucher/edit"/>" method="post">
                                    <div class="row ml-1 mr-1 mb-3">
                                        <div class="col-md-6 mb-3"><label for="voucherId" class="labels">Mã giảm giá</label><input id="voucherId" type="text" class="form-control" name="voucherId" value="${voucher.voucherId}" maxlength="25" readonly></div>
                                        <div class="copy-height-prev col-md-6 mb-3"><label class="labels"></label><p class="exist-id">${requestScope.existId}</p></div>
                                        <div class="col-md-6 mb-3"><label for="minProduct" class="labels">Số sản phẩm MIN</label><input id="minProduct" type="number" min="0" value="${voucher.minProduct}" class="form-control" name="minProduct" required="required"></div>
                                        <div class="col-md-6 mb-3"><label for="minValue" class="labels">Giá trị giỏ hàng MIN</label><input id="minValue" type="number" min="0" value="${voucher.minValue}" class="form-control" name="minValue" required="required"></div>
                                        <div class="col-md-6 mb-3"><label for="discount" class="labels">Giảm (%)</label><input id="discount" type="number" min="0" value="${voucher.discount * 100}" class="form-control" name="discount" required="required"></div>
                                        <div class="copy-height-prev col-md-6 mb-3"></div>
                                        <div class="col-md-6 mb-3"><label for="fromDate" class="labels">HSD từ ngày</label><input id="fromDate" type="date" value="<% out.print(new SimpleDateFormat("yyyy-MM-dd").format(new Date(voucher.getFromDate().getTime()))); %>" class="form-control" name="fromDate"></div>
                                        <div class="col-md-6 mb-3"><label for="toDate" class="labels">HSD đến ngày</label><input id="toDate" type="date" value="<% out.print(new SimpleDateFormat("yyyy-MM-dd").format(new Date(voucher.getToDate().getTime()))); %>" class="form-control" name="toDate"></div>
                                    </div>
                                    <div class="row ml-1 mr-1">
                                        <div class="mt-5 text-center col-md-12 d-flex justify-content-end">
                                            <button class="btn btn-primary ct-button" type="submit"><i class="fa fa-check"></i>&nbsp;Đồng ý</button>
                                            <button class="btn btn-primary ct-button ml-3" type="reset"><i class="fa fa-undo-alt"></i>&nbsp;Nhập lại</button>
                                            <a href="${pageContext.request.contextPath}/admin/voucher" class="btn btn-primary ct-button ml-3"><i class="fa fa-angle-left"></i>&nbsp;Quay lại</a>
                                        </div>
                                    </div>
                                </form>
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
<script>
    $(function () {
        $('div[class*="copy-height-prev"]').each(function () {
            $(this).height(59.163);
        });
    });
</script>

</body>
</html>
