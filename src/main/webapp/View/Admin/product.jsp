<%@ page import="Services.deploy.ProImageService" %>
<%@ page import="java.util.List" %><%--
  User: duckhaidev
  Date: 2/17/2022
  Time: 9:37 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/assets" var="url" />
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Dong Phong</title>
    <!-- BOOTSTRAP STYLES-->
    <link href="${url}/css/bootstrap.css" rel="stylesheet" />
    <!-- FONTAWESOME STYLES-->
    <link href="${url}/fonts/fontawesome-pro-5.15.4-web/css/all.min.css" rel="stylesheet" />
    <!-- MORRIS CHART STYLES-->

    <!-- CUSTOM STYLES-->
    <link href="${url}/css/custom.css" rel="stylesheet" />
    <!-- GOOGLE FONTS-->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
    <!-- TABLE STYLES-->
    <link href="${url}/js/dataTables/dataTables.bootstrap.css" rel="stylesheet" />
</head>
<body>
<div id="wrapper">
    <jsp:include page="navtop.jsp"></jsp:include>
    <!-- /. NAV TOP  -->

    <jsp:include page="navside.jsp"></jsp:include>
    <!-- /. NAV SIDE  -->

    <div id="page-wrapper" >
        <div id="page-inner">
            <div class="row">
                <div class="col-md-12">
                    <h2>Quản Lý Sản Phẩm</h2>
                    <h5>Welcome Jhon Deo , Love to see you back. </h5>

                </div>
            </div>
            <!-- /. ROW  -->
            <hr />

            <div class="row">
                <div class="col-md-12">
                    <!-- Advanced Tables -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Danh mục sản phẩm
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Hình ảnh</th>
                                        <th>Tên sản phẩm</th>
                                        <th>Giá bán</th>
                                        <th>Loại sản phẩm</th>
                                        <th>Thương hiệu</th>
                                        <th>Tác vụ</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${productList}" var="product">
                                            <tr class="odd">
                                                <td>${product.PRO_ID}</td>
                                                <c:url value="/images/product-images?fname=${product.getProReIMG()}" var="imageUrl"></c:url>
                                                <c:choose>
                                                    <c:when test="${product.getProReIMG() == null}">
                                                        <td><i>NULL</i></td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td><img width="50" height="50" src="${imageUrl}" style="object-fit: cover;" alt="Hình ảnh"></td>
                                                    </c:otherwise>
                                                </c:choose>
                                                <td>${product.PRO_NAME}</td>
                                                <td>${product.PRO_PRICE}</td>
                                                <td>${product.CAT.CAT_NAME}</td>
                                                <td>${product.BRA.BRA_NAME}</td>
                                                <td>
                                                    <a href="<c:url value="#"/>" class="text-center">Chi tiết</a>&nbsp;|&nbsp;
                                                    <a href="<c:url value="#"/>" class="text-center">Sửa</a>&nbsp;|&nbsp;
                                                    <a href="<c:url value="#"/>" class="text-center">Xóa</a>
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
<script src="${url}/vendor/jquery/jquery-3.2.1.min.js"></script>
<!-- BOOTSTRAP SCRIPTS -->
<script src="${url}/vendor/bootstrap/js/bootstrap.min.js"></script>
<!-- METISMENU SCRIPTS -->
<script src="${url}/js/jquery.metisMenu.js"></script>
<!-- DATA TABLE SCRIPTS -->
<script src="${url}/js/dataTables/jquery.dataTables.js"></script>
<script src="${url}/js/dataTables/dataTables.bootstrap.js"></script>
<script>
    $(document).ready(function () {
        $('#dataTables-example').dataTable();
    });
</script>
<!-- CUSTOM SCRIPTS -->
<script src="${url}/js/custom.js"></script>


</body>
</html>
