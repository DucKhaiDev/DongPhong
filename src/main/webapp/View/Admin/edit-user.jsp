<%--
  User: duckhaidev
  Date: 2/16/2022
  Time: 10:54 AM
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
    <link href="${url}/css/bootstrap.css" rel="stylesheet"/>
    <!-- FONTAWESOME STYLES-->
    <link href="${url}/fonts/fontawesome-pro-5.15.4-web/css/all.min.css" rel="stylesheet" />
    <!-- CUSTOM STYLES-->
    <link href="${url}/css/custom.css" rel="stylesheet" />
    <!-- GOOGLE FONTS-->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${url}/css/my-account-stylesheet.css">
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
                    <h2>Quản Lý Tài Khoản</h2>
                    <h5>Welcome Jhon Deo , Love to see you back. </h5>

                </div>
            </div>
            <!-- /. ROW  -->
            <hr />
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">Cập Nhật Thông Tin</div>
                        <div class="panel-body">
                            <div class="row">
                                <form action="<c:url value="/admin/user/edit"/>" method="post" enctype="multipart/form-data">
                                    <div class="row">
                                        <div class="col-md-5 border-right">
                                            <div class="d-flex flex-column align-items-center text-center p-3 py-5">
                                                <c:url value="/images/avatar?fname=${user.AVATAR}" var="avatarUrl"></c:url>
                                                <img class="avatar rounded-circle mt-5 mb-1" width="150px" height="150px" style="object-fit: cover;" src="${avatarUrl}" alt="Ảnh đại diện">
                                                <input class="text-center mb-3 file-upload" type="file" name="update_avatar" />
                                                <span class="font-weight-bold mb-2">${user.LASTNAME} ${user.FIRSTNAME}</span>
                                                <span class="text-black-50">ID:&nbsp;${user.USER_ID}</span>
                                            </div>
                                        </div>
                                        <div class="col-md-7 border-right">
                                            <div class="p-3 py-5">
                                                <div class="row mt-2">
                                                    <div class="col-md-6"><label class="labels">Họ</label><input type="text" class="form-control" name="update_lastname" maxlength="255" placeholder="${user.LASTNAME}"></div>
                                                    <div class="col-md-6"><label class="labels">Tên</label><input type="text" class="form-control" name="update_firstname" maxlength="255" placeholder="${user.FIRSTNAME}"></div>
                                                </div>
                                                <div class="row mt-3">
                                                    <div class="col-md-12 mb-3"><label class="labels">Tên đăng nhập</label><input type="text" class="form-control" placeholder="${user.USERNAME}" readonly></div>
                                                    <div class="col-md-12 mb-3"><label class="labels">Mật khẩu</label><input type="text" class="form-control" name="update_password" placeholder="${user.PASSWORD}"></div>
                                                    <div class="col-md-12 mb-3"><label class="labels">Email</label><input type="text" class="form-control" name="update_email" maxlength="255" placeholder="${user.EMAIL}"></div>
                                                    <div class="col-md-12 mb-3"><label class="labels">Địa chỉ</label><input type="text" class="form-control" name="update_address" maxlength="2000" placeholder="${user.ADDRESS}"></div>
                                                    <div class="col-md-12 mb-3"><label class="labels">Số điện thoại</label><input type="text" class="form-control" name="update_phone" maxlength="12" placeholder="${user.PHONE}"></div>
                                                    <div class="col-md-12">
                                                        <label class="labels">Quyền truy cập</label>
                                                        <div class="checkbox mt-0">
                                                            <div class="row">
                                                                <div class="col-md-3"></div>
                                                                <c:choose>
                                                                    <c:when test="${user.ROLE}">
                                                                        <div class="col-md-3"><input type="radio" value="false" name="update_role"> Quản trị viên</div>
                                                                        <div class="col-md-3"><input type="radio" value="true" name="update_role" checked="checked"> Thành viên</div>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <div class="col-md-3"><input type="radio" value="false" name="update_role" checked="checked"> Quản trị viên</div>
                                                                        <div class="col-md-3"><input type="radio" value="true" name="update_role"> Thành viên</div>
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
                                        <div class="mt-5 text-center col-md-6"><button class="btn btn-primary profile-button float-right mr-5" type="submit"><i class="fa fa-user-edit"></i>&nbsp;Cập nhật</button></div>
                                        <div class="mt-5 text-center col-md-6">
                                            <button class="btn btn-primary profile-button float-left ml-5" type="reset"><i class="fa fa-undo-alt"></i>&nbsp;Nhập lại</button>
                                            <div class="float-right pr-3">
                                                <a href="${pageContext.request.contextPath}/admin/user" class="btn btn-primary profile-button">
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
<script src="${url}/vendor/jquery/jquery-3.2.1.min.js"></script>
<!-- BOOTSTRAP SCRIPTS -->
<script src="${url}/vendor/bootstrap/js/bootstrap.min.js"></script>
<!-- METISMENU SCRIPTS -->
<script src="${url}/js/jquery.metisMenu.js"></script>
<!-- CUSTOM SCRIPTS -->
<script src="${url}/js/custom.js"></script>
<!--===============================================================================================-->
<script type="text/javascript">
    $(document).ready(function () {
        var readURL = function (input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();
                reader.onload = function (e) {
                    $('.avatar').attr('src', e.target.result);
                }
                reader.readAsDataURL(input.files[0]);
            }
        }

        $(".file-upload").on('change', function () {
            readURL(this);
        });
    });
</script>

</body>
</html>
