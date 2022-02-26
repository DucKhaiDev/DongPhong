<%--
  User: duckhaidev
  Date: 2/15/2022
  Time: 5:22 PM
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
    <jsp:include page="navtop.jsp"/>
    <!-- /. NAV TOP  -->

    <jsp:include page="navside.jsp"/>
    <!-- /. NAV SIDE  -->

    <div id="page-wrapper" >
        <div id="page-inner">
            <div class="row">
                <div class="col-md-12">
                    <h2>Quản Lý Tài Khoản</h2>
                    <h5>Welcome Jhon Deo , Love to see you back. </h5>

                </div>
            </div>
            <!-- /. ROW  -->
            <hr />

            <div class="row">
                <div class="col-md-12">
                    <!-- Advanced Tables -->
                    <div class="panel panel-default">
                        <div class="panel-heading">Danh sách người dùng</div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>ID</th>
                                            <th>Avatar</th>
                                            <th>Tên đăng nhập</th>
                                            <th>Mật khẩu</th>
                                            <th>Email</th>
                                            <th>Trạng thái</th>
                                            <th>Quyền truy cập</th>
                                            <th>Tác vụ</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:set var="number" value="0"/>
                                        <c:forEach items="${users}" var="user">
                                            <tr class="odd">
                                                <td>${number = number + 1}</td>
                                                <td>${user.USER_ID}</td>
                                                <c:url value="/images/avatar?fname=${user.AVATAR}" var="avatarUrl"/>
                                                <td><img class="user-img-empty" width="50" height="50" <c:if test="${not empty user.AVATAR}">src="${avatarUrl}"</c:if> style="object-fit: cover;"></td>
                                                <td>${user.USERNAME}</td>
                                                <td>${user.PASSWORD}</td>
                                                <td>${user.EMAIL}</td>
                                                <td>Đang hoạt động</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${!user.ROLE}">Quản trị viên</c:when>
                                                        <c:otherwise>Thành viên</c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td>
                                                    <a href="<c:url value="/admin/user/edit?id=${user.USER_ID}"/>" class="text-center">Sửa</a>&nbsp;|&nbsp;
                                                    <a href="<c:url value="/admin/user/delete?id=${user.USER_ID}"/>" class="text-center">Xóa</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
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
            "pagingType": "full_numbers"
        });
    });
</script>
<!-- CUSTOM SCRIPTS -->
<script src="${url}/js/custom.js"></script>


</body>
</html>
