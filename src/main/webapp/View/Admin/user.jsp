<%--
  User: duckhaidev
  Date: 2/15/2022
  Time: 5:22 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Quản Lý Người Dùng - Đồ gỗ Cao cấp Đông Phong</title>
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
                    <h2>Quản Lý Người Dùng</h2>
                    <h5>Chào mừng ${sessionScope.displayName}, rất vui được gặp lại bạn.</h5>

                </div>
            </div>
            <!-- /. ROW  -->
            <hr/>

            <div class="row">
                <div class="col-md-12">
                    <!-- Advanced Tables -->
                    <div class="panel panel-default">
                        <div class="panel-heading">Danh sách người dùng</div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="dataTable">
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
                                    <jsp:useBean id="users" scope="request" type="java.util.List"/>
                                    <c:forEach items="${users}" var="user">
                                        <tr class="odd">
                                            <td>${number = number + 1}</td>
                                            <td>${user.userId}</td>
                                            <c:url value="/images/avatar?fname=${user.avatar}" var="avatarUrl"/>
                                            <td><img class="user-img-empty" width="50" height="50"
                                                     <c:if test="${not empty user.avatar}">src="${avatarUrl}"</c:if>
                                                     style="object-fit: cover;" alt=""></td>
                                            <td>${user.username}</td>
                                            <td>${user.password}</td>
                                            <td>${user.email}</td>
                                            <td>Đang hoạt động</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${!user.role}">Quản trị viên</c:when>
                                                    <c:otherwise>Thành viên</c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>
                                                <a href="<c:url value="/admin/user/edit?id=${user.userId}"/>"
                                                   class="text-center">Sửa</a>&nbsp;|&nbsp;
                                                <c:choose>
                                                    <c:when test="${user.userId == 'FOUNDER'}">
                                                        <a class="text-center isDisabled">Xóa</a>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <form action="<c:url value="/admin/user/delete"/>"
                                                              method="get">
                                                            <input type="hidden" name="id" value="${user.userId}">
                                                            <a class="btn-delete-user text-center cursor-pointer">Xóa</a>
                                                        </form>
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
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/assets/vendor/bootstrap/js/popper.js"></script>
<!-- BOOTSTRAP SCRIPTS -->
<script src="${pageContext.request.contextPath}/assets/vendor/bootstrap/js/bootstrap.min.js"></script>
<!-- METISMENU SCRIPTS -->
<script src="${pageContext.request.contextPath}/assets/js/jquery.metisMenu.js"></script>
<!-- DATA TABLE SCRIPTS -->
<script src="${pageContext.request.contextPath}/assets/js/dataTables/jquery.dataTables.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/dataTables/dataTables.bootstrap.js"></script>
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/assets/vendor/sweetalert/sweetalert.min.js"></script>
<script>
    $(document).ready(function () {
        $('#dataTable').dataTable({
            "pagingType": "full_numbers"
        });
    });
</script>
<!-- CUSTOM SCRIPTS -->
<script src="${pageContext.request.contextPath}/assets/js/custom.js"></script>
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/assets/vendor/sweetalert/sweetalert.min.js"></script>
<script>
    $('.btn-delete-user').each(function () {
        $(this).on('click', function () {
            swal({
                text: 'Xóa người dùng sẽ xóa tất cả các dữ liệu liên quan. Bạn có muốn tiếp tục?',
                icon: 'warning',
                buttons: {
                    confirm: 'Có',
                    cancel: 'Không'
                }
            }).then((willDelete) => {
                if (willDelete) {
                    $(this).parent().submit();
                }
            });
        });
    });
</script>

</body>
</html>
