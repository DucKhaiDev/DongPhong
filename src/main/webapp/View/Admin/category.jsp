<%@ page import="Services.deploy.CategoryService" %>
<%@ page import="Entity.Category" %>
<%--
  User: duckhaidev
  Date: 2/23/2022
  Time: 5:18 PM
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
    <link href="${pageContext.request.contextPath}/assets/css/bootstrap.css" rel="stylesheet" />
    <!-- FONTAWESOME STYLES-->
    <link href="${pageContext.request.contextPath}/assets/fonts/fontawesome-pro-5.15.4-web/css/all.min.css" rel="stylesheet" />
    <!-- MORRIS CHART STYLES-->

    <!-- CUSTOM STYLES-->
    <link href="${pageContext.request.contextPath}/assets/css/custom.css" rel="stylesheet" />
    <!-- GOOGLE FONTS-->
    <link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
    <!-- TABLE STYLES-->
    <link href="${pageContext.request.contextPath}/assets/js/dataTables/dataTables.bootstrap.css" rel="stylesheet" />
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
                    <a href="${pageContext.request.contextPath}/admin/category/add" class="btn btn-primary ct-button float-right">
                        <i class="fa fa-plus"></i>&nbsp;Thêm loại sản phẩm
                    </a>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <!-- Advanced Tables -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Danh mục loại sản phẩm
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>ID</th>
                                        <th>Loại sản phẩm</th>
                                        <th>Danh mục</th>
                                        <th>Mô tả</th>
                                        <th>Tác vụ</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <c:set var="number" value="0"/>
                                        <jsp:useBean id="categories" scope="request" type="java.util.List"/>
                                        <c:forEach items="${categories}" var="category">
                                        <tr class="odd">
                                            <td>${number = number + 1}</td>
                                            <td>${category.categoryId}</td>
                                            <td>${category.categoryName}</td>
                                            <td>${category.room.roomName}</td>
                                            <td>${category.categoryDescription}</td>
                                            <td>
                                                <a href="<c:url value="/admin/category/edit?id=${category.categoryId}"/>" class="text-center">Sửa</a>&nbsp;|&nbsp;
                                                <%
                                                    boolean isUnusedCategory = new CategoryService().isUnusedCategory(((Category)pageContext.getAttribute("category")).getCategoryId());
                                                    request.setAttribute("isUnusedCategory", isUnusedCategory);
                                                %>
                                                <c:choose>
                                                    <c:when test="${isUnusedCategory}">
                                                        <a href="<c:url value="/admin/category/delete?id=${category.categoryId}"/>" class="text-center">Xóa</a>
                                                    </c:when>
                                                    <c:when test="${!isUnusedCategory}">
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
        },
            {
            columnDefs: [
                {
                    targets: 2,
                    render: function (data, type) {
                        return type === 'display' && data.length > 111 ?
                            data.substr(0, 111) + ' (Còn nữa...)' :
                            data;
                    }
                },
                {"width": "5%", "targets": 0},
                {"width": "10%", "targets": 1},
                {"width": "20%", "targets": 2},
                {"width": "10%", "targets": 3},
                {"width": "35%", "targets": 4},
                {"width": "20%", "targets": 5}
            ]
        });
    });
</script>
<!-- CUSTOM SCRIPTS -->
<script src="${pageContext.request.contextPath}/assets/js/custom.js"></script>

</body>
</html>
