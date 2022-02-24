<%@ page import="Services.deploy.BrandService" %>
<%@ page import="Entity.Brand" %><%--
  User: duckhaidev
  Date: 2/24/2022
  Time: 12:53 AM
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

    <div id="page-wrapper" >
        <div id="page-inner">
            <div class="row">
                <div class="col-md-12">
                    <h2>Quản Lý Sản Phẩm</h2>
                    <div class="row">
                        <div class="col-md-12"><h5>Welcome Jhon Deo , Love to see you back. </h5></div>
                    </div>

                </div>
            </div>
            <!-- /. ROW  -->
            <hr />
            <div class="row">
                <div class="col-md-12 mb-3">
                    <a href="${pageContext.request.contextPath}/admin/brand/add" class="btn btn-primary ct-button float-right">
                        <i class="fa fa-plus"></i>&nbsp;Thêm thương hiệu
                    </a>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <!-- Advanced Tables -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Danh mục thương hiệu
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Thương hiệu</th>
                                        <th>Mô tả</th>
                                        <th>Tác vụ</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${brands}" var="brand">
                                        <tr class="odd">
                                            <td>${brand.BRA_ID}</td>
                                            <td>${brand.BRA_NAME}</td>
                                            <td>${brand.BRA_DES}</td>
                                            <td>
                                                <a href="<c:url value="/admin/brand/edit?id=${brand.BRA_ID}"/>" class="text-center">Sửa</a>&nbsp;|&nbsp;
                                                <%
                                                    boolean isUnusedBrand = new BrandService().isUnusedBrand(((Brand)pageContext.getAttribute("brand")).getBRA_ID());
                                                    request.setAttribute("isUnusedBrand", isUnusedBrand);
                                                %>
                                                <c:choose>
                                                    <c:when test="${isUnusedBrand}">
                                                        <a href="<c:url value="/admin/brand/delete?id=${brand.BRA_ID}"/>" class="text-center">Xóa</a>
                                                    </c:when>
                                                    <c:when test="${!isUnusedBrand}">
                                                        <a class="text-center isDisabled">(Đang được sử dụng)</a>
                                                    </c:when>
                                                </c:choose>
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
        $('#dataTables-example').dataTable({
            columnDefs: [
                {
                    targets: 2,
                    render: function ( data, type, row ) {
                        return type === 'display' && data.length > 111 ?
                            data.substr(0, 111) + ' (Còn nữa...)' :
                            data;
                    }
                },
                {"width": "10%", "targets": 0},
                {"width": "20%", "targets": 1},
                {"width": "50%", "targets": 2},
                {"width": "20%", "targets": 3}
            ]
        });
    });
</script>
<!-- CUSTOM SCRIPTS -->
<script src="${url}/js/custom.js"></script>


</body>
</html>

