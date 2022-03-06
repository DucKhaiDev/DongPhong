<%@ page import="Services.deploy.UserService" %>
<%--
  User: duckhaidev
  Date: 2/16/2022
  Time: 10:54 AM
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
                    <div class="panel panel-default">
                        <div class="panel-heading">Cập nhật thông tin</div>
                        <div class="panel-body">
                            <div class="row">
                                <form action="<c:url value="/admin/user/edit"/>" method="post" enctype="multipart/form-data">
                                    <div class="row">
                                        <div class="col-md-5 border-right">
                                            <div class="d-flex flex-column align-items-center text-center p-3 py-5">
                                                <jsp:useBean id="user" scope="request" type="Entity.User"/>
                                                <c:url value="/images/avatar?fname=${user.avatar}" var="avatarUrl"/>
                                                <img class="avatar user-img-empty rounded-circle mt-5 mb-1" width="150px" height="150px" style="object-fit: cover;" <c:if test="${not empty user.avatar}">src="${avatarUrl}"</c:if> alt="">
                                                <input class="text-center mb-3 file-upload" type="file" name="avatar" />
                                                <span class="font-weight-bold mb-2">${user.lastName} ${user.firstName}</span>
                                                <span class="text-black-50">ID:&nbsp;${user.userId}</span>
                                            </div>
                                        </div>
                                        <div class="col-md-7 border-right">
                                            <div class="p-3 py-5">
                                                <div class="row mt-2 mb-3">
                                                    <div class="col-md-6"><label for="lastName" class="labels">Họ</label><input id="lastName" type="text" class="form-control" name="lastName" maxlength="255" placeholder="${user.lastName}"></div>
                                                    <div class="col-md-6"><label for="firstName" class="labels">Tên</label><input id="firstName" type="text" class="form-control" name="firstName" maxlength="255" placeholder="${user.firstName}"></div>
                                                </div>
                                                <div class="row mt-3">
                                                    <div class="col-md-12 mb-3"><label for="username" class="labels">Tên đăng nhập</label><input id="username" type="text" class="form-control" placeholder="${user.username}" readonly></div>
                                                    <div class="col-md-12 mb-3"><label for="password" class="labels">Mật khẩu</label><input id="password" type="text" class="form-control" name="password" placeholder="${user.password}"></div>
                                                    <div class="col-md-12 mb-3"><label for="email" class="labels">Email</label><input id="email" type="text" class="form-control" name="email" maxlength="255" placeholder="${user.email}"></div>
                                                    <div class="col-md-12 mb-3"><label for="address" class="labels">Địa chỉ</label><input id="address" type="text" class="form-control" name="address" maxlength="2000" placeholder="${user.address}"></div>
                                                    <div class="col-md-12 mb-3"><label for="phone" class="labels">Số điện thoại</label><input id="phone" type="text" class="form-control" name="phone" maxlength="12" placeholder="${user.phone}"></div>
                                                    <div class="col-md-12">
                                                        <label class="labels">Quyền truy cập</label>
                                                        <div class="checkbox mt-0">
                                                            <div class="row">
                                                                <div class="col-md-3"></div>
                                                                <c:choose>
                                                                    <c:when test="${user.role}">
                                                                        <div class="col-md-3"><label>
                                                                            <input type="radio" value="false" name="role">
                                                                        </label> Quản trị viên</div>
                                                                        <div class="col-md-3"><label>
                                                                            <input type="radio" value="true" name="role" checked="checked">
                                                                        </label> Thành viên</div>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <div class="col-md-3"><label>
                                                                            <input type="radio" value="false" name="role" checked="checked">
                                                                        </label> Quản trị viên</div>
                                                                        <% int count = new UserService().countAdmin(); %>
                                                                        <div class="col-md-3"><label>
                                                                            <input type="radio" value="true" name="role" <% if (count <= 1) out.print("disabled"); %>>
                                                                        </label> Thành viên</div>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                                <div class="col-md-3"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="mt-5 text-center col-md-6"><button class="btn btn-primary ct-button float-right mr-5" type="submit"><i class="fa fa-check"></i>&nbsp;Đồng ý</button></div>
                                        <div class="mt-5 text-center col-md-6">
                                            <button class="btn btn-primary ct-button float-left ml-5" type="reset"><i class="fa fa-undo-alt"></i>&nbsp;Nhập lại</button>
                                            <div class="float-right pr-3">
                                                <a href="${pageContext.request.contextPath}/admin/user" class="btn btn-primary ct-button">
                                                    <i class="fa fa-angle-left"></i>&nbsp;Quay lại
                                                </a>
                                            </div>
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
<script type="text/javascript">
    $(document).ready(function () {
        const readURL = function (input) {
            if (input.files && input.files[0]) {
                const reader = new FileReader();
                reader.onload = function (e) {
                    $('.avatar').attr('src', e.target.result);
                }
                reader.readAsDataURL(input.files[0]);
            }
        };

        $(".file-upload").on('change', function () {
            readURL(this);
        });
    });
</script>

</body>
</html>
